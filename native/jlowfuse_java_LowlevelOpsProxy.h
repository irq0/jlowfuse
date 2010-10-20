#ifndef JLOWFUSE_JAVA_LOWLEVELOPSPROXY_H
#define JLOWFUSE_JAVA_LOWLEVELOPSPROXY_H

#include <jni.h>

#define SIGNATURE_LINK        "(JJJLjava/lang/String;)V"
#define SIGNATURE_SYMLINK     "(JLjava/lang/String;JLjava/lang/String;)V"
#define SIGNATURE_FORGET      "(JJJ)V"
#define SIGNATURE_GETATTR     "(JJJ)V"
#define SIGNATURE_SETATTR     "(JJJIJ)V"
#define SIGNATURE_READLINK    "(JJ)V"
#define SIGNATURE_MKNOD       "(JJLjava/lang/String;SS)V"
#define SIGNATURE_UNLINK      "(JJLjava/lang/String;)V"
#define SIGNATURE_RMDIR       "(JJLjava/lang/String;)V"
#define SIGNATURE_FSYNC       "(JJIJ)V"
#define SIGNATURE_OPENDIR     "(JJJ)V"
#define SIGNATURE_READDIR     "(JJIIJ)V"
#define SIGNATURE_RELEASEDIR  "(JJJ)V"
#define SIGNATURE_FSYNCDIR    "(JJIJ)V"
#define SIGNATURE_STATFS      "(JJ)V"
#define SIGNATURE_SETXATTR    "(JJLjava/lang/String;Ljava/nio/ByteBuffer;II)V"
#define SIGNATURE_GETXATTR    "(JJLjava/lang/String;I)V"
#define SIGNATURE_LISTXATTR   "(JJI)V"
#define SIGNATURE_REMOVEXATTR "(JJLjava/lang/String;)V"
#define SIGNATURE_BMAP        "(JJIJ)V"
#define SIGNATURE_INIT        "(JJ)V"
#define SIGNATURE_WRITE       "(JJLjava/nio/ByteBuffer;IIJ)V"
#define SIGNATURE_DESTROY     "(J)V"
#define SIGNATURE_FLUSH       "(JJJ)V"
#define SIGNATURE_LOOKUP      "(JJLjava/lang/String;)V"
#define SIGNATURE_READ        "(JJIIJ)V"
#define SIGNATURE_CREATE      "(JJLjava/lang/String;S)V"
#define SIGNATURE_MKDIR       "(JJLjava/lang/String;S)V"
#define SIGNATURE_RENAME      "(JJLjava/lang/String;JLjava/lang/String;)V"
#define SIGNATURE_RELEASE     "(JJJ)V"
#define SIGNATURE_OPEN        "(JJJ)V"
#define SIGNATURE_ACCESS      "(JJI)V"

/* struct to represent java class AbstractLowlevelOps and decendents */
struct class_lowlevel_ops {
        jclass class;

        jobject object;
        
        struct {
                jmethodID link;
                jmethodID symlink;
                jmethodID forget;
                jmethodID getattr;
                jmethodID setattr;
                jmethodID readlink;
                jmethodID mknod;
                jmethodID unlink;
                jmethodID rmdir;
                jmethodID fsync;
                jmethodID opendir;
                jmethodID readdir;
                jmethodID releasedir;
                jmethodID fsyncdir;
                jmethodID statfs;
                jmethodID getxattr;
                jmethodID setxattr;
                jmethodID listxattr;
                jmethodID removexattr;
                jmethodID bmap;
                jmethodID init;
                jmethodID write;
                jmethodID destroy;
                jmethodID flush;
                jmethodID lookup;
                jmethodID read;
                jmethodID create;
                jmethodID mkdir;
                jmethodID rename;
                jmethodID release;
                jmethodID open;
                jmethodID access;
        } method;

};

struct class_lowlevel_ops *alloc_class_lowlevel_ops(JNIEnv *env);
void free_class_lowlevel_ops(JNIEnv *env, struct class_lowlevel_ops *ptr);
void populate_class_lowlevel_ops(JNIEnv *env, struct class_lowlevel_ops *dst,
                                  jobject obj);

#endif
