%module FuseExtra
%{
#ifndef SWIG_FUSE_EXTRA_I
#define SWIG_FUSE_EXTRA_I

#define FUSE_USE_VERSION 26
#include <fuse_lowlevel.h>
#include <fuse_opt.h>
#include <sys/statvfs.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <fcntl.h>
#include "fuse_extra.h"
%}

%include "fuse_types.i"

%rename(Dirbuf) dirbuf;
struct dirbuf {
	void *p;
	size_t size;
};

%rename(replyBufLimited) fuse_extra_reply_buf_limited;
%rename(dirbufAdd) fuse_extra_dirbuf_add;
extern int fuse_extra_reply_buf_limited(fuse_req_t req, const void *buf,
                                        size_t bufsize, off_t off,
                                        size_t maxsize);
extern void fuse_extra_dirbuf_add(fuse_req_t req, struct dirbuf *b,
                                  const char *name, fuse_ino_t ino,
                                  mode_t mode);
%{
#endif
%}
