%module FuseRequest
%{
#ifndef SWIG_FUSE_REQUEST_I
#define SWIG_FUSE_REQUEST_I

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

/* FUSE requrest functions */
%rename(getContext) fuse_req_ctx;

const struct fuse_ctx *fuse_req_ctx(fuse_req_t req);

%{
#endif
%}
