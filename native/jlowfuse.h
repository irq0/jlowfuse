#ifndef JLOWFUSE_H
#define JLOWFUSE_H

#define FUSE_USE_VERSION 26

#include <jni.h>
#include <fuse_lowlevel.h>
#include <fuse_opt.h>

JNIEnv *attach_native_thread();
void detach_native_thread();

void jlowfuse_init(void *userdata, struct fuse_conn_info *conn);
void jlowfuse_destroy(void *userdata);
void jlowfuse_statfs(fuse_req_t req, fuse_ino_t ino);
void jlowfuse_readlink(fuse_req_t req, fuse_ino_t ino);
void jlowfuse_flush(fuse_req_t req, fuse_ino_t ino, struct fuse_file_info *fi);
void jlowfuse_open(fuse_req_t req, fuse_ino_t ino, struct fuse_file_info *fi);
void jlowfuse_getattr(fuse_req_t req, fuse_ino_t ino, struct fuse_file_info *fi);
void jlowfuse_release(fuse_req_t req, fuse_ino_t ino, struct fuse_file_info *fi);
void jlowfuse_opendir(fuse_req_t req, fuse_ino_t ino, struct fuse_file_info *fi);
void jlowfuse_releasedir(fuse_req_t req, fuse_ino_t ino, struct fuse_file_info *fi);
void jlowfuse_lookup(fuse_req_t req, fuse_ino_t ino, const char* name);
void jlowfuse_unlink(fuse_req_t req, fuse_ino_t ino, const char* name);
void jlowfuse_rmdir(fuse_req_t req, fuse_ino_t ino, const char* name);
void jlowfuse_removexattr(fuse_req_t req, fuse_ino_t ino, const char* name);
void jlowfuse_read(fuse_req_t req, fuse_ino_t ino, size_t size, off_t off, struct fuse_file_info *fi);
void jlowfuse_readdir(fuse_req_t req, fuse_ino_t ino, size_t size, off_t off, struct fuse_file_info *fi);
void jlowfuse_fsync(fuse_req_t req, fuse_ino_t ino, int datasync, struct fuse_file_info *fi);
void jlowfuse_fsyncdir(fuse_req_t req, fuse_ino_t ino, int datasync, struct fuse_file_info *fi);
void jlowfuse_forget(fuse_req_t req, fuse_ino_t ino, unsigned long nlookup);
void jlowfuse_setattr(fuse_req_t req, fuse_ino_t ino, struct stat *attr, int to_set, struct fuse_file_info *fi);
void jlowfuse_mknod(fuse_req_t req, fuse_ino_t parent, const char* name, mode_t mode, dev_t rdev);
void jlowfuse_mkdir(fuse_req_t req, fuse_ino_t parent, const char* name, mode_t mode);
void jlowfuse_symlink(fuse_req_t req, const char *link, fuse_ino_t parent, const char* name);
void jlowfuse_rename(fuse_req_t req, fuse_ino_t parent, const char* name, fuse_ino_t newparent, const char* newname);
void jlowfuse_link(fuse_req_t req, fuse_ino_t ino, fuse_ino_t newparent, const char* newname);
void jlowfuse_write(fuse_req_t req, fuse_ino_t ino, const char *buf, size_t size, off_t off, struct fuse_file_info *fi);
void jlowfuse_create(fuse_req_t req, fuse_ino_t parent, const char *name,  mode_t mode, struct fuse_file_info *fi);
void jlowfuse_setxattr(fuse_req_t req, fuse_ino_t ino, const char *name, const char *value, size_t size, int flags);
void jlowfuse_getxattr(fuse_req_t req, fuse_ino_t ino, const char *name, size_t size);
void jlowfuse_listxattr(fuse_req_t req, fuse_ino_t ino, size_t size);
void jlowfuse_access(fuse_req_t req, fuse_ino_t ino, int mask);
void jlowfuse_bmap(fuse_req_t req, fuse_ino_t ino, size_t blocksize, uint64_t idx);


#endif
