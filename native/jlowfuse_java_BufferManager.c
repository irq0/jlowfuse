/**
 * JNI Wrapper for LowlevelOps classes
 */

#include <stdlib.h>
#include <assert.h>
#include <err.h>

#include "jlowfuse.h"
#include "jlowfuse_java_BufferManager.h"

struct class_buffermanager *alloc_class_buffermanager(JNIEnv *env)
{
        struct class_buffermanager *result;

        result = calloc(1, sizeof(struct class_buffermanager));
        assert(result != NULL);

        return result;
}

void free_class_buffermanager(JNIEnv *env, struct class_buffermanager *ptr)
{
        assert(ptr         != NULL &&
               ptr->class  != NULL &&
               ptr->object != NULL);

        (*env)->DeleteGlobalRef(env, ptr->class);
        (*env)->DeleteGlobalRef(env, ptr->object);

        free(ptr);
}

void populate_class_buffermanager(JNIEnv *env, struct class_buffermanager *dst,
                                  jobject obj)
{
        jclass class;

        class = (*env)->GetObjectClass(env, obj);
        assert(class != NULL);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->class = (*env)->NewGlobalRef(env, class);
        dst->object = (*env)->NewGlobalRef(env, obj);

        dst->method.take = (*env)->GetMethodID(env, class, "take", SIGNATURE_TAKE);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.free = (*env)->GetMethodID(env, class, "free", SIGNATURE_FREE);
        if ((*env)->ExceptionCheck(env)) goto exception;

        return;

exception:
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClear(env);
        errx(16, "Exception occured in populate_class_buffermanager");
}

jobject buffermanager_take(JNIEnv *env, struct class_buffermanager *class)
{
	assert(class != NULL);

	jobject result = (*env)->CallObjectMethod(env,
	                                          class->object,
	                                          class->method.take);

        exception_check(env);

        return result;
}

void buffermanager_free(JNIEnv *env, struct class_buffermanager *class, jobject to_free)
{
	assert(class != NULL);

	(*env)->CallVoidMethod(env,
	                       class->object,
	                       class->method.free,
	                       to_free);

        exception_check(env);
}
