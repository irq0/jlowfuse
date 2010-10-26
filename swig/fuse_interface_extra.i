%module fuse_extra
%{
#define FUSE_USE_VERSION 26
#include <fuse_lowlevel.h>
#include <fuse_opt.h>
#include <sys/statvfs.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <fcntl.h>
#include "fuse_extra.c"
%}

%include "fuse_types.i"

struct dirbuf {
	void *p;
	size_t size;
};

extern int fuse_extra_reply_buf_limited(fuse_req_t req, const char *buf,
                                        size_t bufsize, off_t off,
                                        size_t maxsize);
extern void fuse_extra_dirbuf_add(fuse_req_t req, struct dirbuf *b,
                                  const char *name, fuse_ino_t ino);

