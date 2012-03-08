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

	jboolean use_for_read;
	jboolean use_for_write;
};

#define use_buffermanager_for_read(class) (((class) != NULL) && (class->use_for_read == JNI_TRUE))
#define use_buffermanager_for_write(class) (((class) != NULL) && (class->use_for_write == JNI_TRUE))

struct class_buffermanager *alloc_class_buffermanager(JNIEnv *env);
void free_class_buffermanager(JNIEnv *env, struct class_buffermanager *ptr);
void populate_class_buffermanager(JNIEnv *env, struct class_buffermanager *dst,
                                  jobject obj);

jobject buffermanager_take(JNIEnv *env, struct class_buffermanager *class);
void buffermanager_free(JNIEnv *env, struct class_buffermanager *class, jobject to_free);
#endif
