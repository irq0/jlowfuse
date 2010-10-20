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

void jlowfuse_init(void *userdata, struct fuse_conn_info *conn) 
{

        JNIEnv *env = attach_native_thread();
        
        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.init);
        
        detach_native_thread();
}

void jlowfuse_statfs(fuse_req_t req, fuse_ino_t ino) 
{
        JNIEnv *env = attach_native_thread();
        
        printf("[statfs] req: %p %li \n", req, req);
        
        (*env)->CallVoidMethod(env,
                               cl_low_ops->object,
                               cl_low_ops->method.statfs,
                               req,
                               ino);
        
        if ((*env)->ExceptionCheck(env)) {
                (*env)->ExceptionDescribe(env);
                (*env)->ExceptionClear(env);
        }
        printf("[statfs] return: %i \n");

        detach_native_thread();
}
