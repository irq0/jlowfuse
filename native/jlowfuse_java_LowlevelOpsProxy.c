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

#include "jlowfuse_java_LowlevelOpsProxy.h"

struct class_lowlevel_ops *alloc_class_lowlevel_ops(JNIEnv *env)
{
        struct class_lowlevel_ops *result;

        result = calloc(1, sizeof(struct class_lowlevel_ops));
        assert(result != NULL);

        return result;
}
        
void free_class_lowlevel_ops(JNIEnv *env, struct class_lowlevel_ops *ptr)
{
        assert(ptr         != NULL &&
               ptr->class  != NULL &&
               ptr->object != NULL);
        
        (*env)->DeleteGlobalRef(env, ptr->class);
        (*env)->DeleteGlobalRef(env, ptr->object);
        
        free(ptr);
}

void populate_class_lowlevel_ops(JNIEnv *env, struct class_lowlevel_ops *dst,
                                  jobject obj)
{
        jclass class;

        class = (*env)->GetObjectClass(env, obj);
        assert(class != NULL);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->class = (*env)->NewGlobalRef(env, class);
        dst->object = (*env)->NewGlobalRef(env, obj);
        
        dst->method.link = (*env)->GetMethodID(env, class, "link", SIGNATURE_LINK);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.symlink = (*env)->GetMethodID(env, class, "symlink", SIGNATURE_SYMLINK);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.forget = (*env)->GetMethodID(env, class, "forget", SIGNATURE_FORGET);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.getattr = (*env)->GetMethodID(env, class, "getattr", SIGNATURE_GETATTR);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.setattr = (*env)->GetMethodID(env, class, "setattr", SIGNATURE_SETATTR);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.readlink = (*env)->GetMethodID(env, class, "readlink", SIGNATURE_READLINK);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.mknod = (*env)->GetMethodID(env, class, "mknod", SIGNATURE_MKNOD);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.unlink = (*env)->GetMethodID(env, class, "unlink", SIGNATURE_UNLINK);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.rmdir = (*env)->GetMethodID(env, class, "rmdir", SIGNATURE_RMDIR);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.fsync = (*env)->GetMethodID(env, class, "fsync", SIGNATURE_FSYNC);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.opendir = (*env)->GetMethodID(env, class, "opendir", SIGNATURE_OPENDIR);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.readdir = (*env)->GetMethodID(env, class, "readdir", SIGNATURE_READDIR);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.releasedir = (*env)->GetMethodID(env, class, "releasedir", SIGNATURE_RELEASEDIR);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.fsyncdir = (*env)->GetMethodID(env, class, "fsyncdir", SIGNATURE_FSYNCDIR);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.statfs = (*env)->GetMethodID(env, class, "statfs", SIGNATURE_STATFS);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.getxattr = (*env)->GetMethodID(env, class, "getxattr", SIGNATURE_GETXATTR);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.setxattr = (*env)->GetMethodID(env, class, "setxattr", SIGNATURE_SETXATTR);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.listxattr = (*env)->GetMethodID(env, class, "listxattr", SIGNATURE_LISTXATTR);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.removexattr = (*env)->GetMethodID(env, class, "removexattr", SIGNATURE_REMOVEXATTR);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.bmap = (*env)->GetMethodID(env, class, "bmap", SIGNATURE_BMAP);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.init = (*env)->GetMethodID(env, class, "init", SIGNATURE_INIT);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.write = (*env)->GetMethodID(env, class, "write", SIGNATURE_WRITE);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.destroy = (*env)->GetMethodID(env, class, "destroy", SIGNATURE_DESTROY);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.flush = (*env)->GetMethodID(env, class, "flush", SIGNATURE_FLUSH);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.lookup = (*env)->GetMethodID(env, class, "lookup", SIGNATURE_LOOKUP);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.read = (*env)->GetMethodID(env, class, "read", SIGNATURE_READ);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.create = (*env)->GetMethodID(env, class, "create", SIGNATURE_CREATE);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.mkdir = (*env)->GetMethodID(env, class, "mkdir", SIGNATURE_MKDIR);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.rename = (*env)->GetMethodID(env, class, "rename", SIGNATURE_RENAME);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.release = (*env)->GetMethodID(env, class, "release", SIGNATURE_RELEASE);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.open = (*env)->GetMethodID(env, class, "open", SIGNATURE_OPEN);
        if ((*env)->ExceptionCheck(env)) goto exception;

        dst->method.access = (*env)->GetMethodID(env, class, "access", SIGNATURE_ACCESS);
        if ((*env)->ExceptionCheck(env)) goto exception;
        
        return;
        
exception:
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClear(env);
        errx(16, "Exception occured in populate_class_lowlevel_ops");
}
