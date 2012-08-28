/*
 * Copyright (c) 2011 Marcel Lauhoff.
 *
 * This file is part of jlowfuse.
 *
 * jlowfuse is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jlowfuse is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jlowfuse.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * fuse c ops -> java ops wrapper.
 * Basically handles types on the c side and calls methods in JLowFuseOpsProxy.
 *
 * @author Marcel Lauhoff <ml@irq0.org>
 */

#define FUSE_USE_VERSION 26

#include "jlowfuse.h"
#include "jlowfuse_java_LowlevelOpsProxy.h"
#include "jlowfuse_java_BufferManager.h"

#include <jni.h>
#include <fuse_lowlevel.h>
#include <fuse_opt.h>
#include <err.h>
#include <string.h>

extern struct class_lowlevel_ops *cl_low_ops;
extern struct class_buffermanager *cl_bufman;

void exception_check(JNIEnv *env);


#define JLOWFUSE_OPERATION_REQ_INO_FI(operation)                        \
        void jlowfuse_##operation(fuse_req_t req, fuse_ino_t ino,       \
                                  struct fuse_file_info *fi)            \
        {                                                               \
                JNIEnv *env = attach_native_thread();                   \
									\
                							\
                (*env)->CallVoidMethod(env,                             \
                                       cl_low_ops->object,              \
                                       cl_low_ops->method.operation,    \
                                       (jlong)req,                      \
                                       (jlong)ino,                      \
                                       (jlong)fi);                      \
                                                                        \
                exception_check(env);                                   \
                detach_native_thread();                                 \
        }

#define JLOWFUSE_OPERATION_REQ_INO_NAME(operation)                      \
        void jlowfuse_##operation(fuse_req_t req, fuse_ino_t parent,    \
                                  const char* name)                     \
        {                                                               \
                JNIEnv *env = attach_native_thread();                   \
                jstring jname;                                          \
									\
	  \
                jname = (*env)->NewStringUTF(env, name);                \
                if (jname == NULL) {                                    \
	                errx(16, "##operation: Cannot allocate jstring"); \
                }                                                       \
                                                                        \
                (*env)->CallVoidMethod(env,                             \
                                       cl_low_ops->object,              \
                                       cl_low_ops->method.operation,    \
                                       (jlong)req,                      \
                                       (jlong)parent,                   \
                                       jname);                          \
                                                                        \
                exception_check(env);                                   \
                detach_native_thread();                                 \
        }

#define JLOWFUSE_OPERATION_REQ_INO(operation)                           \
        void jlowfuse_##operation(fuse_req_t req, fuse_ino_t parent)    \
        {                                                               \
                JNIEnv *env = attach_native_thread();                   \
                                                                        \
                (*env)->CallVoidMethod(env,                             \
                                       cl_low_ops->object,              \
                                       cl_low_ops->method.operation,    \
                                       (jlong)req,                      \
                                       (jlong)parent);                  \
                                                                        \
                exception_check(env);                                   \
                detach_native_thread();                                 \
        }

#define JLOWFUSE_OPERATION_REQ_INO_SIZE_OFF_FI(operation)               \
        void jlowfuse_##operation(fuse_req_t req, fuse_ino_t ino,       \
                                  size_t size, off_t off,               \
                                  struct fuse_file_info *fi)            \
        {                                                               \
                JNIEnv *env = attach_native_thread();                   \
                                                                        \
                (*env)->CallVoidMethod(env,                             \
                                       cl_low_ops->object,              \
                                       cl_low_ops->method.operation,    \
                                       (jlong)req,                      \
                                       (jlong)ino,                      \
                                       (jlong)size,	                \
                                       (jlong)off,                      \
                                       (jlong)fi);                      \
                                                                        \
                exception_check(env);                                   \
                detach_native_thread();                                 \
        }

#define JLOWFUSE_OPERATION_REQ_INO_DATASYNC_FI(operation)               \
        void jlowfuse_##operation(fuse_req_t req, fuse_ino_t ino,       \
                                  int datasync,                         \
                                  struct fuse_file_info *fi)            \
        {                                                               \
                JNIEnv *env = attach_native_thread();                   \
                                                                        \
                (*env)->CallVoidMethod(env,                             \
                                       cl_low_ops->object,              \
                                       cl_low_ops->method.operation,    \
                                       (jlong)req,                      \
                                       (jlong)ino,                      \
                                       (jint)datasync,                  \
                                       (jlong)fi);                      \
                                                                        \
                exception_check(env);                                   \
                detach_native_thread();                                 \
        }


JLOWFUSE_OPERATION_REQ_INO_FI(flush)
JLOWFUSE_OPERATION_REQ_INO_FI(open)
JLOWFUSE_OPERATION_REQ_INO_FI(getattr)
JLOWFUSE_OPERATION_REQ_INO_FI(release)
JLOWFUSE_OPERATION_REQ_INO_FI(opendir)
JLOWFUSE_OPERATION_REQ_INO_FI(releasedir)

JLOWFUSE_OPERATION_REQ_INO_NAME(lookup)
JLOWFUSE_OPERATION_REQ_INO_NAME(unlink)
JLOWFUSE_OPERATION_REQ_INO_NAME(rmdir)
JLOWFUSE_OPERATION_REQ_INO_NAME(removexattr)

JLOWFUSE_OPERATION_REQ_INO(readlink)
JLOWFUSE_OPERATION_REQ_INO(statfs)

JLOWFUSE_OPERATION_REQ_INO_SIZE_OFF_FI(read)
JLOWFUSE_OPERATION_REQ_INO_SIZE_OFF_FI(readdir)

JLOWFUSE_OPERATION_REQ_INO_DATASYNC_FI(fsync)
JLOWFUSE_OPERATION_REQ_INO_DATASYNC_FI(fsyncdir)

// TODO
void jlowfuse_init(void *userdata, struct fuse_conn_info *conn)
{

        JNIEnv *env = attach_native_thread();

        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.init);

        exception_check(env);
        detach_native_thread();
}

void jlowfuse_destroy(void *userdata)
{

        JNIEnv *env = attach_native_thread();

        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.destroy);

        exception_check(env);
        detach_native_thread();
}


void jlowfuse_forget(fuse_req_t req, fuse_ino_t ino, unsigned long nlookup)
{
        JNIEnv *env = attach_native_thread();

        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.forget,
                               (jlong)req,
                               (jlong)ino,
                               (jlong)nlookup);

        exception_check(env);
        detach_native_thread();
}


void jlowfuse_setattr(fuse_req_t req, fuse_ino_t ino, struct stat *attr,
                      int to_set, struct fuse_file_info *fi)
{
        JNIEnv *env = attach_native_thread();

        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.setattr,
                               (jlong)req,
                               (jlong)ino,
                               (jlong)attr,
                               (jint)to_set,
                               (jlong)fi);

        exception_check(env);
        detach_native_thread();
}

void jlowfuse_mknod(fuse_req_t req, fuse_ino_t parent, const char* name, mode_t mode,
                    dev_t rdev)
{
        JNIEnv *env = attach_native_thread();
        jstring jname;

        jname = (*env)->NewStringUTF(env, name);
        if (jname == NULL) {
                errx(16, "mknod: Cannot allocate jstring");
        }

        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.mknod,
                               (jlong)req,
                               (jlong)parent,
                               jname,
                               (jshort)mode,
                               (jshort)rdev);

        exception_check(env);
        detach_native_thread();
}

void jlowfuse_mkdir(fuse_req_t req, fuse_ino_t parent, const char* name, mode_t mode)
{
        JNIEnv *env = attach_native_thread();
        jstring jname;

        jname = (*env)->NewStringUTF(env, name);
        if (jname == NULL) {
                errx(16, "mkdir: Cannot allocate jstring");
        }

       (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.mkdir,
                               (jlong)req,
                               (jlong)parent,
                               jname,
                               (jshort)mode);

        exception_check(env);
        detach_native_thread();
}

void jlowfuse_symlink(fuse_req_t req, const char *link, fuse_ino_t parent, const char* name)
{
        JNIEnv *env = attach_native_thread();
        jstring jlink;
        jstring jname;

        jlink = (*env)->NewStringUTF(env, link);
        if (jlink == NULL) {
                errx(16, "symlink: Cannot allocate jstring");
        }

        jname = (*env)->NewStringUTF(env, name);
        if (jname == NULL) {
                errx(16, "symlink: Cannot allocate jstring");
        }

        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.symlink,
                               (jlong)req,
                               jlink,
                               (jlong)parent,
                               jname);

        exception_check(env);
        detach_native_thread();
}

void jlowfuse_rename(fuse_req_t req, fuse_ino_t parent, const char* name,
                                     fuse_ino_t newparent, const char* newname)
{
        JNIEnv *env = attach_native_thread();
        jstring jname;
        jstring jnewname;

        jname = (*env)->NewStringUTF(env, name);
        if (jname == NULL) {
                errx(16, "rename: Cannot allocate jstring");
        }

        jnewname = (*env)->NewStringUTF(env, newname);
        if (jnewname == NULL) {
                errx(16, "rename: Cannot allocate jstring");
        }

        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.rename,
                               (jlong)req,
                               (jlong)parent,
                               jname,
                               (jlong)newparent,
                               jnewname);

        exception_check(env);
        detach_native_thread();
}

void jlowfuse_link(fuse_req_t req, fuse_ino_t ino,
                   fuse_ino_t newparent, const char* newname)
{
        JNIEnv *env = attach_native_thread();
        jstring jnewname;

        jnewname = (*env)->NewStringUTF(env, newname);
        if (jnewname == NULL) {
                errx(16, "link: Cannot allocate jstring");
        }

        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.link,
                               (jlong)req,
                               (jlong)ino,
                               (jlong)newparent,
                               jnewname);

        exception_check(env);
        detach_native_thread();
}

void jlowfuse_write(fuse_req_t req, fuse_ino_t ino, const char *buf,
                    size_t size, off_t off, struct fuse_file_info *fi)
{
        jobject jbuf;
        JNIEnv *env = attach_native_thread();

        if (use_buffermanager_for_write(cl_bufman)) {
	        jbuf = buffermanager_take(env, cl_bufman);
	        char *jbuf_ptr = (*env)->GetDirectBufferAddress(env, jbuf);
	        memcpy(jbuf_ptr, buf, size);
        } else {
	        jbuf = (*env)->NewDirectByteBuffer(env, (void*)buf, size);
        }

        if (jbuf == NULL) {
                errx(16, "write: Cannot allocate ByteBuffer");
        }

        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.write,
                               (jlong)req,
                               (jlong)ino,
                               jbuf,
                               (jlong)size,
                               (jlong)off,
                               (jlong)fi);

        exception_check(env);
        detach_native_thread();
}

void jlowfuse_create(fuse_req_t req, fuse_ino_t parent, const char *name,
                     mode_t mode, struct fuse_file_info *fi)
{
        JNIEnv *env = attach_native_thread();
        jstring jname;

        jname = (*env)->NewStringUTF(env, name);
        if (jname == NULL) {
                errx(16, "crate: Cannot allocate jstring");
        }

        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.create,
                               (jlong)req,
                               (jlong)parent,
                               jname,
                               (jint)mode,
                               (jlong)fi);

        exception_check(env);
        detach_native_thread();
}

void jlowfuse_setxattr(fuse_req_t req, fuse_ino_t ino, const char *name,
                       const char *value, size_t size, int flags)
{
        JNIEnv *env = attach_native_thread();
        jobject jvalue;
        jstring jname;

        jvalue = (*env)->NewDirectByteBuffer(env, (void*)value, size);
        if (jvalue == NULL) {
                errx(16, "setxattr: Cannot allocate ByteBuffer");
        }

        jname = (*env)->NewStringUTF(env, name);
        if (jname == NULL) {
                errx(16, "setxattr: Cannot allocate jstring");
        }


        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.setxattr,
                               (jlong)req,
                               (jlong)ino,
                               jname,
                               jvalue,
                               (jint)size,
                               (jint)flags);

        exception_check(env);
        detach_native_thread();
}

void jlowfuse_getxattr(fuse_req_t req, fuse_ino_t ino, const char *name,
                       size_t size)
{
        JNIEnv *env = attach_native_thread();
        jstring jname;

        jname = (*env)->NewStringUTF(env, name);
        if (jname == NULL) {
                errx(16, "getxattr: Cannot allocate jstring");
        }

        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.getxattr,
                               (jlong)req,
                               (jlong)ino,
                               jname,
                               (jint)size);

        exception_check(env);
        detach_native_thread();
}

void jlowfuse_listxattr(fuse_req_t req, fuse_ino_t ino, size_t size)
{
        JNIEnv *env = attach_native_thread();

        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.listxattr,
                               (jlong)req,
                               (jlong)ino,
                               (jint)size);

        exception_check(env);
        detach_native_thread();
}

void jlowfuse_access(fuse_req_t req, fuse_ino_t ino, int mask)
{
        JNIEnv *env = attach_native_thread();

        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.access,
                               (jlong)req,
                               (jlong)ino,
                               (jint)mask);

        exception_check(env);
        detach_native_thread();
}

void jlowfuse_bmap(fuse_req_t req, fuse_ino_t ino, size_t blocksize, uint64_t idx)
{
        JNIEnv *env = attach_native_thread();

        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.bmap,
                               (jlong)req,
                               (jlong)ino,
                               (jint)blocksize,
                               (jlong)idx);

        exception_check(env);
        detach_native_thread();
}

/****
UNIMPLEMENTED OPERATIONS:

void(* 	getlk )(fuse_req_t req, fuse_ino_t ino, struct fuse_file_info *fi, struct flock *lock)

void(* 	setlk )(fuse_req_t req, fuse_ino_t ino, struct fuse_file_info *fi, struct flock *lock, int sleep)

void(* 	ioctl )(fuse_req_t req, fuse_ino_t ino, int cmd, void *arg, struct fuse_file_info *fi, unsigned *flagsp, const void *in_buf, size_t in_bufsz, size_t out_bufszp)

void(* 	poll )(fuse_req_t req, fuse_ino_t ino, struct fuse_file_info *fi, struct fuse_pollhandle *ph)

****/
