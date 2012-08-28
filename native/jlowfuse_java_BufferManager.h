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
