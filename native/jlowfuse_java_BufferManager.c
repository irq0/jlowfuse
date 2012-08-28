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
