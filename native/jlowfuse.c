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
        jmethodID destroy;
        jmethodID lookup;
} method_ids;

jobject opts_object;
JNIEnv *jni_env; /* this only works with a _single_ java thread */


void jlowfuse_init(void *userdata, struct fuse_conn_info *conn) 
{
        jobject result;

        printf("enter init\n");
        
        
        result = (*jni_env)->CallObjectMethod(jni_env, opts_object, method_ids.init,
                                          NULL);
        
        printf("yeeeeeehaa! - C\n");
        printf("result: %i\n", result);
        
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
          .statfs      = NULL,
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

JNIEXPORT jint JNICALL Java_org_irq0_jlowfuse_JLowFuse_init
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
        
        printf("multi: %i\n", multi_threaded);
        
        
        // get methodID for fuse operations
        jclass opts_class = (*env)->GetObjectClass(env, opts_obj);

        method_ids.init =
          (*env)->GetMethodID(env, opts_class, "init",
                  "(Ljava/nio/ByteBuffer;)Lorg/irq0/jlowfuse/reply/Reply;");
        
        if (method_ids.init == NULL) {
                return;
        }

        opts_object = opts_obj;
        
        
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


