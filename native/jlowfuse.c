/*
 * jlowfuse.c - java FUSE lowlevel api wapper
 *
 * NOTE: this only works with a _single_ java thread.
 * No mapping of native to java threads
 * 
 */


#define FUSE_USE_VERSION 26

#include "jlowfuse.h"
#include "jlowfuse_java_LowlevelOpsProxy.h"

#include <jni.h>

#include <fuse_lowlevel.h>
#include <fuse_opt.h>

#include <sys/statvfs.h>

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <fcntl.h>
#include <assert.h>

#include <err.h>

JavaVM *jvm;
jobject thread_group;  /* attached java threads */

/* cached class, object, methodids */
struct class_lowlevel_ops *cl_low_ops;

/* attach native thread to java vm */
JNIEnv *attach_native_thread()
{
	JNIEnv *env;
	JavaVMAttachArgs args;
        int res;
                
	args.version = JNI_VERSION_1_6;
	args.name = NULL;
	args.group = thread_group;

	res = (*jvm)->GetEnv(jvm, (void**) &env, args.version);

        if (res == JNI_EDETACHED) {
                (*jvm)->AttachCurrentThreadAsDaemon(jvm,
                                                    (void**) &env,
                                                    (void*) &args);
        } else if (res < JNI_OK) { /* should not happen */
                errx(16, "should not happen.. GetEnv result: %i", res);
        }
        
        return env;
}

/* detach native thread from java vm */
void detach_native_thread()
{
        int res;
        return;
        res = (*jvm)->DetachCurrentThread(jvm);
        assert(res == JNI_OK);
}        



JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *ljvm, void *reserved)
{
        JNIEnv *env;
        int ret;
        
        jvm = ljvm;

        ret = (*jvm)->GetEnv(jvm, (void **)&env, JNI_VERSION_1_2);
        assert(ret != JNI_EVERSION);

        return JNI_VERSION_1_6;
}

struct fuse_lowlevel_ops jlowfuse_ops = {
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

/* register JLowFuseProxy methods */
JNIEXPORT jlong JNICALL Java_jlowfuse_JLowFuse_setOps
(JNIEnv *env, jclass cls, jobject ops_obj)
{
        jlong jresult = 0;
        struct fuse_lowlevel_ops *result = 0;

        printf("env: %p\n", env);
        
        cl_low_ops = alloc_class_lowlevel_ops(env);        
        populate_class_lowlevel_ops(env, cl_low_ops, ops_obj);

        printf("obj: %p\n", ops_obj);

        return (long)&jlowfuse_ops;
}

/* parse fuse commandline from java string array */
JNIEXPORT jlong JNICALL Java_jlowfuse_FuseArgs_makeFuseArgs
(JNIEnv *env, jclass cls, jobjectArray jstrarr)
{
        jsize len;
        jstring jstr;
        
        int i;
        struct fuse_args *args;
        char *str;
        char *mount_point;
        int multi_threaded = -1;
        int foreground = -1;
        
        args = calloc(1, sizeof(struct fuse_args));
        
        len = (*env)->GetArrayLength(env, jstrarr);

        for(i=0; i<len; i++) {
                jstr = (*env)->GetObjectArrayElement(env, jstrarr, i);
                str = (*env)->GetStringUTFChars(env, jstr, NULL);
                assert(str != NULL);

                fuse_opt_add_arg(args, str);

                (*env)->ReleaseStringUTFChars(env, jstr, str);
                (*env)->DeleteLocalRef(env, jstr);
                
        }

        if (fuse_parse_cmdline(args, &mount_point,
                               &multi_threaded, &foreground) != 0) {
                errx(2, "Commandline error");
        }

        return (long)args;        
}


JNIEXPORT jint JNICALL Java_jlowfuse_JLowFuse_init
(JNIEnv *env, jobject obj, jobject ops_obj) 
{
        struct fuse_chan *chan;
        struct fuse_args args = FUSE_ARGS_INIT(0, NULL);
        struct fuse_session *sess; 
        char *mount_point;
        int multi_threaded = -1;
        int foreground = -1;
        int err = -1;

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
        
        chan = fuse_mount(mount_point, NULL);
        sess = fuse_lowlevel_new(NULL, &jlowfuse_ops,
                                 sizeof(jlowfuse_ops), NULL);
        
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

