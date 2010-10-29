%module FuseReply
%{
#ifndef SWIG_FUSE_REPLY_I
#define SWIG_FUSE_REPLY_I

#define FUSE_USE_VERSION 26
#include <fuse_lowlevel.h>
#include <fuse_opt.h>
#include <sys/statvfs.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <fcntl.h>
%}

%include "fuse_types.i"
%include "fuse_structs.i"

%include "various.i"
%include "buffers.i"

/* FUSE reply functions */
%rename(err) fuse_reply_err;
%rename(none) fuse_reply_none;
%rename(entry) fuse_reply_entry;
%rename(create) fuse_reply_create;
%rename(attr) fuse_reply_attr;
%rename(readlink) fuse_reply_readlink;
%rename(open) fuse_reply_open;
%rename(write) fuse_reply_write;
%rename(buf) fuse_reply_buf;
%rename(ivo) fuse_reply_iov;
%rename(statfs) fuse_reply_statfs;
%rename(xattr) fuse_reply_xattr;
%rename(lock) fuse_reply_lock;
%rename(bmap) fuse_reply_bmap;
%rename(ioctlRetry) fuse_reply_ioctl_retry;
%rename(ioctl) fuse_reply_ioctl;
%rename(ioctlIov) fuse_reply_ioctl_iov;
%rename(poll) fuse_reply_poll;

extern int fuse_reply_err(fuse_req_t req, int err);
extern void fuse_reply_none(fuse_req_t req);
extern int fuse_reply_entry(fuse_req_t req, const struct fuse_entry_param *e);
extern int fuse_reply_create(fuse_req_t req, const struct fuse_entry_param *e,
              		     const struct fuse_file_info *fi);
extern int fuse_reply_attr(fuse_req_t req, const struct stat *attr,
                           double attr_timeout);
extern int fuse_reply_readlink(fuse_req_t req, const char *link);
extern int fuse_reply_open(fuse_req_t req, const struct fuse_file_info *fi);
extern int fuse_reply_write(fuse_req_t req, size_t count);
extern int fuse_reply_buf(fuse_req_t req, const void *dirbuf, size_t size);
extern int fuse_reply_iov(fuse_req_t req, const struct iovec *iov, int count);
extern int fuse_reply_statfs(fuse_req_t req, const struct statvfs *stbuf);
extern int fuse_reply_xattr(fuse_req_t req, size_t count);
extern int fuse_reply_lock(fuse_req_t req, struct flock *lock);
extern int fuse_reply_bmap(fuse_req_t req, uint64_t idx);
extern int fuse_reply_ioctl_retry(fuse_req_t req,
                                  const struct iovec *in_iov, size_t in_count,
              			  const struct iovec *out_iov, size_t out_count);
%apply void* BUFF {void* buf}
extern int fuse_reply_ioctl(fuse_req_t req, int result, const void *buf, size_t size);
extern int fuse_reply_ioctl_iov(fuse_req_t req, int result, const struct iovec *iov,
			        int count);
extern int fuse_reply_poll(fuse_req_t req, unsigned revents);

%{
#endif
%}
