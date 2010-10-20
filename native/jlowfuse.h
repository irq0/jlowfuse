#ifndef JLOWFUSE_H
#define JLOWFUSE_H

#define FUSE_USE_VERSION 26

#include <jni.h>
#include <fuse_lowlevel.h>
#include <fuse_opt.h>

JNIEnv *attach_native_thread();
void detach_native_thread();

void jlowfuse_init(void *userdata, struct fuse_conn_info *conn);
void jlowfuse_statfs(fuse_req_t req, fuse_ino_t ino);

#endif
