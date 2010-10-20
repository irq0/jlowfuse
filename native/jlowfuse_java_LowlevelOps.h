#ifndef JLOWFUSE_JAVA_LOWLEVELOPS_H
#define JLOWFUSE_JAVA_LOWLEVELOPS_H

#include <jni.h>

#define SIGNATURE_LINK        "(Ljlowfuse/FuseReq;JJLjava/lang/String;)V"                      
#define SIGNATURE_SYMLINK     "(Ljlowfuse/FuseReq;Ljava/lang/String;JLjava/lang/String;)V"     
#define SIGNATURE_FORGET      "(Ljlowfuse/FuseReq;JJ)V"                                        
#define SIGNATURE_GETATTR     "(Ljlowfuse/FuseReq;J)V"                                         
#define SIGNATURE_SETATTR     "(Ljlowfuse/FuseReq;JLfuse/stat;I)V"                             
#define SIGNATURE_READLINK    "(Ljlowfuse/FuseReq;J)V"                                         
#define SIGNATURE_MKNOD       "(Ljlowfuse/FuseReq;JLjava/lang/String;SS)V"                     
#define SIGNATURE_UNLINK      "(Ljlowfuse/FuseReq;JLjava/lang/String;)V"                       
#define SIGNATURE_RMDIR       "(Ljlowfuse/FuseReq;JLjava/lang/String;)V"                       
#define SIGNATURE_FSYNC       "(Ljlowfuse/FuseReq;JI)V"                                        
#define SIGNATURE_OPENDIR     "(Ljlowfuse/FuseReq;J)V"                                         
#define SIGNATURE_READDIR     "(Ljlowfuse/FuseReq;JII)V"                                       
#define SIGNATURE_RELEASEDIR  "(Ljlowfuse/FuseReq;J)V"                                         
#define SIGNATURE_FSYNCDIR    "(Ljlowfuse/FuseReq;JI)V"                                        
#define SIGNATURE_STATFS      "(Ljlowfuse/FuseReq;J)V"                                         
#define SIGNATURE_SETXATTR    "(Ljlowfuse/FuseReq;JLjava/lang/String;Ljava/nio/ByteBuffer;II)V"
#define SIGNATURE_GETXATTR    "(Ljlowfuse/FuseReq;JLjava/lang/String;I)V"                      
#define SIGNATURE_LISTXATTR   "(Ljlowfuse/FuseReq;JI)V"                                        
#define SIGNATURE_REMOVEXATTR "(Ljlowfuse/FuseReq;JLjava/lang/String;)V"                       
#define SIGNATURE_BMAP        "(Ljlowfuse/FuseReq;JIJ)V"                                       
#define SIGNATURE_INIT        "(Ljava/nio/ByteBuffer;)V"                                       
#define SIGNATURE_WRITE       "(Ljlowfuse/FuseReq;JLjava/nio/ByteBuffer;II)V"                  
#define SIGNATURE_DESTROY     "(Ljava/nio/ByteBuffer;)V"                                       
#define SIGNATURE_FLUSH       "(Ljlowfuse/FuseReq;J)V"                                         
#define SIGNATURE_LOOKUP      "(Ljlowfuse/FuseReq;JLjava/lang/String;)V"                       
#define SIGNATURE_READ        "(Ljlowfuse/FuseReq;JII)V"                                       
#define SIGNATURE_CREATE      "(Ljlowfuse/FuseReq;JLjava/lang/String;S)V"                      
#define SIGNATURE_MKDIR       "(Ljlowfuse/FuseReq;JLjava/lang/String;S)V"                      
#define SIGNATURE_RENAME      "(Ljlowfuse/FuseReq;JLjava/lang/String;JLjava/lang/String;)V"    
#define SIGNATURE_RELEASE     "(Ljlowfuse/FuseReq;J)V"                                         
#define SIGNATURE_OPEN        "(Ljlowfuse/FuseReq;J)V"                                         
#define SIGNATURE_ACCESS      "(Ljlowfuse/FuseReq;JI)V"

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
