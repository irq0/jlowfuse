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

/* Help SWIG with types */
typedef unsigned long long int uint64_t;
typedef struct { int __val[2]; } __fsid_t;
typedef unsigned long fuse_ino_t;

%apply unsigned long long { uint64_t }
%apply unsigned long long int { __fsblkcnt64_t }
%apply unsigned long long int { __fsfilcnt64_t }
%apply unsigned long long int { __off64_t }
%apply unsigned long long int { __dev_t }
%apply unsigned int { __mode_t }
%apply unsigned int { __uid_t }
%apply unsigned int { __gid_t }
%apply unsigned int { __nlink_t }
%apply long int { __blksize_t }
%apply long long int { __blkcnt64_t }

%apply fuse_ino_t { __ino_t }
%apply fuse_ino_t { __ino64_t }
%apply unsigned long { fuse_ino_t } 

%apply long int { __time_t }

%include "various.i"


/* FUSE session functions */
extern void fuse_session_add_chan(struct fuse_session *se, struct fuse_chan *ch);
extern void fuse_session_remove_chan(struct fuse_chan *ch);
extern struct fuse_chan *fuse_session_next_chan(struct fuse_session *se,
                                                struct fuse_chan *ch);
extern void fuse_session_exit(struct fuse_session *se);
extern void fuse_session_reset(struct fuse_session *se);
extern int fuse_session_exited(struct fuse_session *se);
extern int fuse_session_loop(struct fuse_session *se);
extern int fuse_session_loop_mt(struct fuse_session *se);

extern struct fuse_session *fuse_lowlevel_new(struct fuse_args *args,
                                       const struct fuse_lowlevel_ops *op,
                                       size_t op_size, void *userdata);


/* FUSE common functions */
extern struct fuse_chan *fuse_mount(const char *mountpoint, struct fuse_args *args);
extern void fuse_unmount(const char *mountpoint, struct fuse_chan *ch);
extern int fuse_set_signal_handlers(struct fuse_session *se);
extern void fuse_remove_signal_handlers(struct fuse_session *se);
extern int fuse_version(void);


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
        int flags;
        unsigned long fh_old;
        int writepage;
        unsigned int direct_io : 1;
        unsigned int keep_cache : 1;
        unsigned int flush : 1;
        unsigned int nonseekable : 1;
        unsigned int padding : 28;
        uint64_t fh;
        uint64_t lock_owner;
};

/* stat (GNU C Library) [cpp -D_FILE_OFFSET_BITS=64 /usr/include/sys/stat.h] */
struct stat
  {
    __dev_t st_dev;
    unsigned short int __pad1;
    __ino_t __st_ino;
    __mode_t st_mode;
    __nlink_t st_nlink;
    __uid_t st_uid;
    __gid_t st_gid;
    __dev_t st_rdev;
    unsigned short int __pad2;
    __off64_t st_size;
    __blksize_t st_blksize;
    __blkcnt64_t st_blocks;
    struct timespec st_atim;
    struct timespec st_mtim;
    struct timespec st_ctim;
    __ino64_t st_ino;
  };

/* timespect (GNU C Library) [cpp -D_FILE_OFFSET_BITS=64 /usr/include/time.h] */
struct timespec
  {
    __time_t tv_sec;            /* Seconds.  */
    long int tv_nsec;           /* Nanoseconds.  */
  };


/* iovec (GNU C Library) [cpp -D_FILE_OFFSET_BITS=64 /usr/include/sys/uio.h] */
struct iovec
  {
    void *iov_base;
    size_t iov_len;
  };

struct fuse_args {
        /** Argument count */
        int argc;

        /** Argument vector.  NULL terminated */
        %apply char **STRING_ARRAY { char **argv };
        char **argv;

        /** Is 'argv' allocated? */
        int allocated;
};
