/*
 * jlowfuse.c - java FUSE lowlevel api wapper
 *
 * NOTE: this only works with a _single_ java thread.
 * No mapping of native to java threads
 * 
 */


#define FUSE_USE_VERSION 26

#include "jlowfuse.h"

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

JNIEnv *jni_env; 



struct class_lowlevel_opts *alloc_class_lowlevel_opts(JNIEnv *env)
{
        struct class_lowlevel_opts *result;

        result = calloc(1, sizeof(struct class_lowlevel_opts));
        assert(result != NULL);

        return result;
}
        
void free_class_lowlevel_opts(JNIEnv *env, struct class_lowlevel_opts *ptr)
{
        if (ptr->class != NULL) {
                (*env)->DeleteGlobalRef(env, ptr->class);
        }
        free(ptr);
}

void populate_class_lowlevel_opts(JNIEnv *env, struct class_lowlevel_opts *dst,
                                  jobject obj)
{
        jclass class;

        class = (*env)->GetObjectClass(env, obj);
        assert(class != NULL);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->class = class;
        dst->object = obj;
        
        dst->method.link = (*env)->GetMethodID(env, class, "link", SIGNATURE_LINK);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.symlink = (*env)->GetMethodID(env, class, "symlink", SIGNATURE_SYMLINK);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.forget = (*env)->GetMethodID(env, class, "forget", SIGNATURE_FORGET);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.getattr = (*env)->GetMethodID(env, class, "getattr", SIGNATURE_GETATTR);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.setattr = (*env)->GetMethodID(env, class, "setattr", SIGNATURE_SETATTR);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.readlink = (*env)->GetMethodID(env, class, "readlink", SIGNATURE_READLINK);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.mknod = (*env)->GetMethodID(env, class, "mknod", SIGNATURE_MKNOD);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.unlink = (*env)->GetMethodID(env, class, "unlink", SIGNATURE_UNLINK);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.rmdir = (*env)->GetMethodID(env, class, "rmdir", SIGNATURE_RMDIR);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.fsync = (*env)->GetMethodID(env, class, "fsync", SIGNATURE_FSYNC);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.opendir = (*env)->GetMethodID(env, class, "opendir", SIGNATURE_OPENDIR);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.readdir = (*env)->GetMethodID(env, class, "readdir", SIGNATURE_READDIR);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.releasedir = (*env)->GetMethodID(env, class, "releasedir", SIGNATURE_RELEASEDIR);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.fsyncdir = (*env)->GetMethodID(env, class, "fsyncdir", SIGNATURE_FSYNCDIR);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.statfs = (*env)->GetMethodID(env, class, "statfs", SIGNATURE_STATFS);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.getxattr = (*env)->GetMethodID(env, class, "getxattr", SIGNATURE_GETXATTR);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.setxattr = (*env)->GetMethodID(env, class, "setxattr", SIGNATURE_SETXATTR);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.listxattr = (*env)->GetMethodID(env, class, "listxattr", SIGNATURE_LISTXATTR);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.removexattr = (*env)->GetMethodID(env, class, "removexattr", SIGNATURE_REMOVEXATTR);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.bmap = (*env)->GetMethodID(env, class, "bmap", SIGNATURE_BMAP);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.init = (*env)->GetMethodID(env, class, "init", SIGNATURE_INIT);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.write = (*env)->GetMethodID(env, class, "write", SIGNATURE_WRITE);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.destroy = (*env)->GetMethodID(env, class, "destroy", SIGNATURE_DESTROY);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.flush = (*env)->GetMethodID(env, class, "flush", SIGNATURE_FLUSH);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.lookup = (*env)->GetMethodID(env, class, "lookup", SIGNATURE_LOOKUP);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.read = (*env)->GetMethodID(env, class, "read", SIGNATURE_READ);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.create = (*env)->GetMethodID(env, class, "create", SIGNATURE_CREATE);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.mkdir = (*env)->GetMethodID(env, class, "mkdir", SIGNATURE_MKDIR);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.rename = (*env)->GetMethodID(env, class, "rename", SIGNATURE_RENAME);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.release = (*env)->GetMethodID(env, class, "release", SIGNATURE_RELEASE);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.open = (*env)->GetMethodID(env, class, "open", SIGNATURE_OPEN);
        if ((*env)-ExceptionCheck(env)) goto exception;

        dst->method.access = (*env)->GetMethodID(env, class, "access", SIGNATURE_ACCESS);
        if ((*env)-ExceptionCheck(env)) goto exception;
        
        return;
        
exception:
        fprintf(stderr, "Exception occured in populate_class_lowlevel_opts\n");
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClear(env);
        exit(23);
}

        
void jlowfuse_init(void *userdata, struct fuse_conn_info *conn) 
{
//        (*jni_env)->CallVoidMethod(jni_env, objects.fs_opts,
//                                   method_ids.init,
//                                   NULL);
        printf("init: C\n");
}


void jlowfuse_statfs(fuse_req_t req, fuse_ino_t ino) 
{
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


JNIEXPORT jlong JNICALL Java_jlowfuse_JLowFuse_setOpsObject
(JNIEnv *env, jobject obj, jobject ops_obj)
{
        jlong jresult = 0;
        struct fuse_lowlevel_ops *result = 0;
        
        struct class_lowlevel_opts *clops;
        clops = alloc_class_lowlevel_opts(env);
        
        populate_class_lowlevel_opts(env, clops, ops_obj);

        result = &jlowfuse_opts;

        *(struct fuse_lowlevel_ops**)&jresult = result;
        return jresult;
        
}

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

