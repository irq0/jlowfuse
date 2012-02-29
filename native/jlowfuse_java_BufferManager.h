#ifndef JLOWFUSE_JAVA_BUFFERMANAGER_H
#define JLOWFUSE_JAVA_BUFFERMANAGER_H

#include <jni.h>

#define SIGNATURE_TAKE     "()Ljava/nio/ByteBuffer;"
#define SIGNATURE_FREE     "(Ljava/nio/ByteBuffer;)V"

/* struct to represent java class AbstractLowlevelOps and decendents */
struct class_buffermanager {
        jclass class;

        jobject object;

        struct {
                jmethodID take;
                jmethodID free;
        } method;

};

#define use_buffermanager(class) ((class) != NULL)

struct class_buffermanager *alloc_class_buffermanager(JNIEnv *env);
void free_class_buffermanager(JNIEnv *env, struct class_buffermanager *ptr);
void populate_class_buffermanager(JNIEnv *env, struct class_buffermanager *dst,
                                  jobject obj);

jobject buffermanager_take(JNIEnv *env, struct class_buffermanager *class);
void buffermanager_free(JNIEnv *env, struct class_buffermanager *class, jobject to_free);
#endif
