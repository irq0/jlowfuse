/*
 * jlowfuse.c - java FUSE lowlevel api wapper
 *
 * NOTE: this only works with a _single_ java thread.
 * No mapping of native to java threads
 * 
 */


#define FUSE_USE_VERSION 26

#include <jni.h>

#include <fuse_lowlevel.h>
#include <fuse_opt.h>

#include <sys/statvfs.h>

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <fcntl.h>

struct jlowfuse_method_ids {
        jmethodID init;
        jmethodID statfs;
        jmethodID destroy;
        jmethodID lookup;
        jmethodID getErr;
        jmethodID statvfs_getArray;
} method_ids;

struct jlowfuse_jclasses {
        jclass error;
        jclass reply;
        jclass fs_opts;
        jclass statvfs;
} classes;

struct jlowfuse_jobjects {
        jobject fs_opts;
} objects;

JNIEnv *jni_env; 


void find_java_methodid_or_die(jmethodID *dst, jclass class, char* name, char* sig)
{
        *dst = (*jni_env)->GetMethodID(jni_env, class, name, sig);           
        if (*dst == NULL) {
                fprintf(stderr, "cannot find methodid for: %s signature: %s\n",
                        name, sig);
                exit(23);
        }
}

void find_java_class_or_die(jclass *dst, char* sig)
{
        *dst = (*jni_env)->FindClass(jni_env, sig);
        
        if (*dst == NULL) {
                fprintf(stderr, "cannot find class %s.. exiting\n", sig);
                exit(23);
        }
}

void jlowfuse_init(void *userdata, struct fuse_conn_info *conn) 
{
        (*jni_env)->CallVoidMethod(jni_env, objects.fs_opts,
                                   method_ids.init,
                                   NULL);
        printf("init: C\n");
}


void jlowfuse_statfs(fuse_req_t req, fuse_ino_t ino) 
{
        jobject result;
        jint err;
        struct statvfs *stat = calloc(sizeof(struct statvfs), 1);
        jlongArray jarr;
        jlong* statarr;
        jboolean isCopy;
        jint len;

        result = (*jni_env)->CallObjectMethod(jni_env, objects.fs_opts,
                                              method_ids.statfs,
                                              NULL);
        if((*jni_env)->IsInstanceOf(jni_env, result, classes.error)) {
                err = (*jni_env)->CallIntMethod(jni_env, result,
                                                method_ids.getErr,
                                                NULL);
                fuse_reply_err(req, err);

        } else if((*jni_env)->IsInstanceOf(jni_env, result, classes.statvfs)) {
                jarr = (*jni_env)->CallObjectMethod(jni_env, result,
                                                    method_ids.statvfs_getArray,
                                                    NULL);
                len = (*jni_env)->GetArrayLength(jni_env, jarr);
                printf("len: %i\n", len);
                
                statarr = calloc(sizeof(jlong), len);
                (*jni_env)->GetLongArrayRegion(jni_env, jarr, 0, len, statarr);

                printf("elem %li\n", statarr[0]);

                
                stat->f_bavail = (long) statarr[0];
                stat->f_bfree = statarr[1];
                stat->f_blocks = statarr[2];
                stat->f_favail = statarr[3];
                stat->f_ffree = statarr[4];
                stat->f_files = statarr[5];
                stat->f_bsize = statarr[6];
                stat->f_flag = statarr[7];
                stat->f_frsize = statarr[8];
                stat->f_fsid = statarr[9];
                stat->f_namemax = statarr[10];

                (*jni_env)->DeleteLocalRef(jni_env, jarr);
              
                fuse_reply_statfs(req, stat);
        }
        printf("init: C\n");
        
        
}


static struct fuse_lowlevel_ops jlowfuse_opts = {
          .init        = jlowfuse_init,
          .destroy     = NULL,
          .lookup      = NULL,
          .forget      = NULL,
          .getattr     = NULL,
          .setattr     = NULL,
          .readlink    = NULL,
          .mknod       = NULL,
          .mkdir       = NULL,
          .unlink      = NULL,
          .rmdir       = NULL,
          .symlink     = NULL,
          .rename      = NULL,
          .link        = NULL,
          .open        = NULL,
          .read        = NULL,
          .write       = NULL,
          .flush       = NULL,
          .release     = NULL,
          .fsync       = NULL,
          .opendir     = NULL,
          .readdir     = NULL,
          .releasedir  = NULL,
          .fsyncdir    = NULL,
          .statfs      = jlowfuse_statfs,
          .setxattr    = NULL,
          .getxattr    = NULL,
          .listxattr   = NULL,
          .removexattr = NULL,
          .access      = NULL,
          .create      = NULL,
          .getlk       = NULL,
          .setlk       = NULL,
          .bmap        = NULL,
          /* .ioctl       = NULL,*/
          /* .poll        = NULL, */
};

JNIEXPORT jint JNICALL Java_jlowfuse_JLowFuse_init
(JNIEnv *env, jobject obj, jobject opts_obj) 
{
        struct fuse_chan *chan;
        struct fuse_args args = FUSE_ARGS_INIT(0, NULL);
        struct fuse_session *sess; 
        char *mount_point;
        int multi_threaded = -1;
        int foreground = -1;
        int err = -1;

        jni_env = env;
        
        fuse_opt_add_arg(&args, "-d");
        fuse_opt_add_arg(&args, "/mnt1");
        
        /* parse commandline options */
        if (fuse_parse_cmdline(&args, &mount_point,
                               &multi_threaded, &foreground) != 0) {
                //    printf("cannot parse commandline :(");
                return 2;
        }
        
        if (!mount_point) {
                printf("specify mountpoint\n");
                return 2;
        }

        // precache classes, methodIDs
        classes.fs_opts = (*env)->GetObjectClass(env, opts_obj);
        objects.fs_opts = opts_obj;

        find_java_class_or_die(&classes.error,
                               "org/irq0/jlowfuse/reply/FsError");
        find_java_class_or_die(&classes.reply,
                               "org/irq0/jlowfuse/reply/Reply");
        find_java_class_or_die(&classes.statvfs,
                               "org/irq0/jlowfuse/reply/Statvfs");
        find_java_methodid_or_die(&method_ids.getErr, classes.error, "getErr", "()I");
        find_java_methodid_or_die(&method_ids.init, classes.fs_opts, "init",
                           "(Ljava/nio/ByteBuffer;)V");
        find_java_methodid_or_die(&method_ids.statfs, classes.fs_opts, "statfs",
                                  "(J)Lorg/irq0/jlowfuse/reply/Reply;");
        find_java_methodid_or_die(&method_ids.statvfs_getArray,
                                  classes.statvfs, "getArray",
                                  "()[J");
        
        
        
        chan = fuse_mount(mount_point, NULL);
        sess = fuse_lowlevel_new(NULL, &jlowfuse_opts,
                                 sizeof(jlowfuse_opts), NULL);
        
        printf("chan=%p sess=%p\n", chan, sess);
        
//        fuse_set_signal_handlers(sess);
        
        fuse_session_add_chan(sess, chan);
        err = fuse_session_loop(sess);
        
        fuse_remove_signal_handlers(sess);
        fuse_session_remove_chan(chan);
        
        fuse_session_destroy(sess);
        fuse_session_exit(sess);
        
        fuse_unmount(mount_point, chan);
        
//        fuse_opt_free_args(&args);
        
        return err;
}

