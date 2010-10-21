/**
 * fuse c ops -> java ops wrapper.
 * Basically handles types on the c side and calls methods in JLowFuseOpsProxy.
 *
 * @author Marcel Lauhoff <ml@irq0.org>
 */

#define FUSE_USE_VERSION 26

#include "jlowfuse.h"
#include "jlowfuse_java_LowlevelOpsProxy.h"

#include <jni.h>
#include <fuse_lowlevel.h>
#include <fuse_opt.h>

extern struct class_lowlevel_ops *cl_low_ops;


void exception_check(JNIEnv *env)
{ 
        if ((*env)->ExceptionCheck(env)) {
                (*env)->ExceptionDescribe(env);
                (*env)->ExceptionClear(env);
        }
}       

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

void jlowfuse_statfs(fuse_req_t req, fuse_ino_t ino) 
{
        JNIEnv *env = attach_native_thread();
        
        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.statfs,
                               (jlong)&req,
                               (jlong)ino);

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

void jlowfuse_lookup(fuse_req_t req, fuse_ino_t parent, char* name) 
{
        JNIEnv *env = attach_native_thread();
        jstring jname;

        jname = (*env)->NewStringUTF(env, name);
        if (jname == NULL) {
                errx(16, "lookup: Cannot allocate jstring");
        }
        
        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.lookup,
                               (jlong)&req,
                               (jlong)parent,
                               jname);
        
        exception_check(env);
        detach_native_thread();
}

void jlowfuse_forget(fuse_req_t req, fuse_ino_t ino, unsigned long nlookup) 
{
        JNIEnv *env = attach_native_thread();

        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.forget,
                               (jlong)&req,
                               (jlong)ino,
                               (jlong)nlookup);
        
        exception_check(env);
        detach_native_thread();
}

void jlowfuse_getattr(fuse_req_t req, fuse_ino_t ino, struct fuse_file_info *fi) 
{
        JNIEnv *env = attach_native_thread();
        
        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.getattr,
                               (jlong)&req,
                               (jlong)ino,
                               (jlong)fi);
        
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
                               (jlong)&req,
                               (jlong)ino,
                               (jlong)attr,
                               (jint)to_set,
                               (jlong)fi);
        
        exception_check(env);
        detach_native_thread();
}

void jlowfuse_readlink(fuse_req_t req, fuse_ino_t ino) 
{
        JNIEnv *env = attach_native_thread();
        
        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.readlink,
                               (jlong)&req,
                               (jlong)ino);
        
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
                               (jlong)&req,
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
                               (jlong)&req,
                               (jlong)parent,                               
                               jname,
                               (jshort)mode);
        
        exception_check(env);
        detach_native_thread();
}

void jlowfuse_unlink(fuse_req_t req, fuse_ino_t parent, const char* name)
{
        JNIEnv *env = attach_native_thread();
        jstring jname;

        jname = (*env)->NewStringUTF(env, name);
        if (jname == NULL) {
                errx(16, "unlink: Cannot allocate jstring");
        }
        
        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.unlink,
                               (jlong)&req,
                               (jlong)parent,                               
                               jname);
        
        exception_check(env);
        detach_native_thread();
}

void jlowfuse_rmdir(fuse_req_t req, fuse_ino_t parent, const char* name)
{
        JNIEnv *env = attach_native_thread();
        jstring jname;

        jname = (*env)->NewStringUTF(env, name);
        if (jname == NULL) {
                errx(16, "rmdir: Cannot allocate jstring");
        }
        
        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.rmdir,
                               (jlong)&req,
                               (jlong)parent,                               
                               jname);
        
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
                               (jlong)&req,
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
                               (jlong)&req,
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
                               (jlong)&req,
                               (jlong)ino,
                               (jlong)newparent,
                               jnewname);

        exception_check(env);
        detach_native_thread();
}

void jlowfuse_open(fuse_req_t req, fuse_ino_t ino, struct fuse_file_info *fi) 
{
        JNIEnv *env = attach_native_thread();
        
        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.open,
                               (jlong)&req,
                               (jlong)ino,
                               (jlong)fi);
        
        exception_check(env);
        detach_native_thread();
}

void jlowfuse_read(fuse_req_t req, fuse_ino_t ino, size_t size, off_t off,
                   struct fuse_file_info *fi) 
{
        JNIEnv *env = attach_native_thread();
        
        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.read,
                               (jlong)&req,
                               (jlong)ino,
                               (jint)size,
                               (jint)off,
                               (jlong)fi);
        
        exception_check(env);
        detach_native_thread();
}


    public void write(long req, long ino, ByteBuffer buf, int size, int off, long fi) {
        ops.write(new FuseReq(req), ino, buf, off);
    }

    public void flush(long req, long ino, long fi) {
        ops.flush(new FuseReq(req), ino);
    }

    public void release(long req, long ino, long fi) {
        ops.release(new FuseReq(req), ino);
    }

    public void fsync(long req, long ino, int datasync, long fi) {
        ops.fsync(new FuseReq(req), ino, datasync);
    }

    public void opendir(long req, long ino, long fi) {
        ops.opendir(new FuseReq(req), ino);
    }

    public void readdir(long req, long ino, int size, int off, long fi) {
        ops.readdir(new FuseReq(req), ino, size, off);
    }

    public void releasedir(long req, long ino, long fi) {
        ops.releasedir(new FuseReq(req), ino);
    }

    public void fsyncdir(long req, long ino, int datasync, long fi) {
        ops.fsyncdir(new FuseReq(req), ino, datasync);
    }

    public void statfs(long req, long ino) {
        ops.statfs(new FuseReq(req), ino);
    }

    public void setxattr(long req, long ino, String name,
                  ByteBuffer value, int size, int flags) {
        ops.setxattr(new FuseReq(req), ino, name, value, flags);
    }

    public void getxattr(long req, long ino, String name, int size) {
        ops.getxattr(new FuseReq(req), ino, name, size);
    }

    public void listxattr(long req, long ino, int size) {
        ops.listxattr(new FuseReq(req), ino, size);
    }

    public void removexattr(long req, long ino, String name) {
        ops.removexattr(new FuseReq(req), ino, name);
    }

    public void access(long req, long ino, int mask) {
        ops.access(new FuseReq(req), ino, mask);
    }

    public void create(long req, long parent, String name, short mode) {
        ops.create(new FuseReq(req), parent, name, mode);
    }

    /*    
    public void getlk(long ino, Flock lock) {
    }

    public void setlk(long ino, Flock lock, int sleep) {
    }
    */

    public void bmap(long req, long ino, int blocksize, long idx) {
        ops.bmap(new FuseReq(req), ino, blocksize, idx);
    }

