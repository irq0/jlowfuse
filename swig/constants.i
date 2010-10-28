/* constants required by fuse */

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

%include "enums.swg"
%javaconst(1);

/* Definitions for the flag in `f_flag' (GNU C Library) */
enum {
        ST_RDONLY      = 1,     /* Mount read-only.  */
        ST_NOSUID      = 2,     /* Ignore suid and sgid bits.  */
        ST_NODEV       = 4,     /* Disallow access to device special files.  */
        ST_NOEXEC      = 8,     /* Disallow program execution.  */
        ST_SYNCHRONOUS = 16,    /* Writes are synced at once.  */
        ST_MANDLOCK    = 64,    /* Allow mandatory locks on an FS.  */
        ST_WRITE       = 128,   /* Write on file/directory/symlink.  */
        ST_APPEND      = 256,   /* Append-only file.  */
        ST_IMMUTABLE   = 512,   /* Immutable file.  */
        ST_NOATIME     = 1024,  /* Do not update access times.  */
        ST_NODIRATIME  = 2048,  /* Do not update directory access times.  */
        ST_RELATIME    = 4096   /* Update atime relative to mtime/ctime.  */
};

/* FUSE: Buffer Flags */
%rename(BufFlags) fuse_buf_flags;
enum fuse_buf_flags {
        FUSE_BUF_IS_FD          = (1 << 1),
        FUSE_BUF_FD_SEEK        = (1 << 2),
        FUSE_BUF_FD_RETRY       = (1 << 3),
};

%rename(BufCopyFlags) fuse_buf_copy_flags;
enum fuse_buf_copy_flags {
        FUSE_BUF_NO_SPLICE       = (1 << 1),
        FUSE_BUF_FORCE_SPLICE    = (1 << 2),
        FUSE_BUF_SPLICE_MOVE     = (1 << 2),
        FUSE_BUF_SPLICE_NONBLOCK = (1 << 3),
};

/* FUSE */
#define FUSE_ROOT_ID 1

/* FUSE: `to_set` flags in setattr */
#define FUSE_SET_ATTR_MODE      (1 << 0)
#define FUSE_SET_ATTR_UID       (1 << 1)
#define FUSE_SET_ATTR_GID       (1 << 2)
#define FUSE_SET_ATTR_SIZE      (1 << 3)
#define FUSE_SET_ATTR_ATIME     (1 << 4)
#define FUSE_SET_ATTR_MTIME     (1 << 5)
#define FUSE_SET_ATTR_ATIME_NOW (1 << 7)
#define FUSE_SET_ATTR_MTIME_NOW (1 << 8)

/* FUSE Ioctl flags */
#define FUSE_IOCTL_COMPAT       (1 << 0)
#define FUSE_IOCTL_UNRESTRICTED (1 << 1)
#define FUSE_IOCTL_RETRY        (1 << 2)
#define FUSE_IOCTL_MAX_IOV      256


