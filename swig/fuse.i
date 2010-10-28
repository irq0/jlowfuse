/* FUSE common functions */
%module Fuse
%{
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

%rename(lowlevelNew) fuse_lowlevel_new;
%rename(mount) fuse_mount;
%rename(unmount) fuse_unmount;
%rename(setSignalHandlers) fuse_set_signal_handlers;
%rename(removeSignalHandlers) fuse_remove_signal_handlers;
%rename(version) fuse_version;

extern struct fuse_session *fuse_lowlevel_new(struct fuse_args *args,
                                       const struct fuse_lowlevel_ops *op,
                                       size_t op_size, void *userdata);


extern struct fuse_chan *fuse_mount(const char *mountpoint, struct fuse_args *args);
extern void fuse_unmount(const char *mountpoint, struct fuse_chan *ch);
extern int fuse_set_signal_handlers(struct fuse_session *se);
extern void fuse_remove_signal_handlers(struct fuse_session *se);
extern int fuse_version(void);


