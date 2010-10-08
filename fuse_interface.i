%module fuse
%{
#include <fuse_lowlevel.h>
#include <fuse_opt.h>
#include <sys/statvfs.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <fcntl.h>
%}

%pointer_functions(fuse_req_t, fuse_req);

/* This causes swig to map uint64_t to BigInteger */
typedef unsigned long long int uint64_t;
%apply unsigned long long { uint64_t }
%apply unsigned long long int { __fsblkcnt64_t }
%apply unsigned long long int { __fsfilcnt64_t }

/* FUSE reply functions */
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
extern int fuse_reply_buf(fuse_req_t req, const char *buf, size_t size);
extern int fuse_reply_iov(fuse_req_t req, const struct iovec *iov, int count);
extern int fuse_reply_statfs(fuse_req_t req, const struct statvfs *stbuf);
extern int fuse_reply_xattr(fuse_req_t req, size_t count);
extern int fuse_reply_lock(fuse_req_t req, struct flock *lock);
extern int fuse_reply_bmap(fuse_req_t req, uint64_t idx);
extern int fuse_reply_ioctl_retry(fuse_req_t req,
                                  const struct iovec *in_iov, size_t in_count,
              			  const struct iovec *out_iov, size_t out_count);
extern int fuse_reply_ioctl(fuse_req_t req, int result, const void *buf, size_t size);
extern int fuse_reply_ioctl_iov(fuse_req_t req, int result, const struct iovec *iov,
			        int count);
extern int fuse_reply_poll(fuse_req_t req, unsigned revents);


/* statvfs (GNU C Library) [cpp -D_FILE_OFFSET_BITS=64 /usr/include/sys/statvfs.h] */
struct statvfs
  {
    unsigned long int f_bsize;
    unsigned long int f_frsize;
    __fsblkcnt64_t f_blocks;
    __fsblkcnt64_t f_bfree;
    __fsblkcnt64_t f_bavail;
    __fsfilcnt64_t f_files;
    __fsfilcnt64_t f_ffree;
    __fsfilcnt64_t f_favail;

    unsigned long int f_fsid;

//    int __f_unused;

    unsigned long int f_flag;
    unsigned long int f_namemax;
//    int __f_spare[6];
  };
  
/* Definitions for the flag in `f_flag' (GNU C Library) */
enum
{
  ST_RDONLY = 1,                /* Mount read-only.  */
#define ST_RDONLY       ST_RDONLY
  ST_NOSUID = 2,                /* Ignore suid and sgid bits.  */
#define ST_NOSUID       ST_NOSUID
  ST_NODEV = 4,                 /* Disallow access to device special files.  */
# define ST_NODEV       ST_NODEV
  ST_NOEXEC = 8,                /* Disallow program execution.  */
# define ST_NOEXEC      ST_NOEXEC
  ST_SYNCHRONOUS = 16,          /* Writes are synced at once.  */
# define ST_SYNCHRONOUS ST_SYNCHRONOUS
  ST_MANDLOCK = 64,             /* Allow mandatory locks on an FS.  */
# define ST_MANDLOCK    ST_MANDLOCK
  ST_WRITE = 128,               /* Write on file/directory/symlink.  */
# define ST_WRITE       ST_WRITE
  ST_APPEND = 256,              /* Append-only file.  */
# define ST_APPEND      ST_APPEND
  ST_IMMUTABLE = 512,           /* Immutable file.  */
# define ST_IMMUTABLE   ST_IMMUTABLE
  ST_NOATIME = 1024,            /* Do not update access times.  */
# define ST_NOATIME     ST_NOATIME
  ST_NODIRATIME = 2048,         /* Do not update directory access times.  */
# define ST_NODIRATIME  ST_NODIRATIME
  ST_RELATIME = 4096            /* Update atime relative to mtime/ctime.  */
# define ST_RELATIME    ST_RELATIME
};

/* FUSE: fuse_file_info */
struct fuse_file_info {
        /** Open flags.  Available in open() and release() */
        int flags;

        /** Old file handle, don't use */
        unsigned long fh_old;

        /** In case of a write operation indicates if this was caused by a
            writepage */
        int writepage;

        /** Can be filled in by open, to use direct I/O on this file.
            Introduced in version 2.4 */
        unsigned int direct_io : 1;

        /** Can be filled in by open, to indicate, that cached file data
            need not be invalidated.  Introduced in version 2.4 */
        unsigned int keep_cache : 1;

        /** Indicates a flush operation.  Set in flush operation, also
            maybe set in highlevel lock operation and lowlevel release
            operation.  Introduced in version 2.6 */
        unsigned int flush : 1;

        /** Can be filled in by open, to indicate that the file is not
            seekable.  Introduced in version 2.9 */
        unsigned int nonseekable : 1;

        /** Padding.  Do not use*/
        unsigned int padding : 28;

        /** File handle.  May be filled in by filesystem in open().
            Available in all other file operations */
        uint64_t fh;

        /** Lock owner id.  Available in locking operations and flush */
        uint64_t lock_owner;
};






