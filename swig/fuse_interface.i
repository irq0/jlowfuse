%module fuse
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


%include "various.i"

%include "buffers.i"

/* FUSE session functions */
extern void fuse_session_add_chan(struct fuse_session *se, struct fuse_chan *ch);
extern void fuse_session_remove_chan(struct fuse_chan *ch);
extern struct fuse_chan *fuse_session_next_chan(struct fuse_session *se,
                                                struct fuse_chan *ch);
extern void fuse_session_exit(struct fuse_session *se);
extern void fuse_session_destroy(struct fuse_session *se);
extern void fuse_session_reset(struct fuse_session *se);
extern int fuse_session_exited(struct fuse_session *se);
extern int fuse_session_loop(struct fuse_session *se);
extern int fuse_session_loop_mt(struct fuse_session *se);

//%apply void* BUFF {void* userdata}
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


/* statvfs (GNU C Library) [cpp -D_FILE_OFFSET_BITS=64 /usr/include/sys/statvfs.h] */
%rename(StatVFS) statvfs;
struct statvfs {
        %rename(bsize) f_bsize;
        unsigned long int f_bsize;
        %rename(frsize) f_frsize;
        unsigned long int f_frsize;
        %rename(blocks) f_blocks;
        __fsblkcnt64_t f_blocks;
        %rename(bfree) f_bfree;
        __fsblkcnt64_t f_bfree;
        %rename(bavail) f_bavail;
        __fsblkcnt64_t f_bavail;
        %rename(files) f_files;
        __fsfilcnt64_t f_files;
        %rename(ffree) f_ffress;
        __fsfilcnt64_t f_ffree;
        %rename(favail) f_favail;
        __fsfilcnt64_t f_favail;
        /* man statfs: "Nobody knows what f_fsid is supposed to contain" */
        %rename(fsid) f_fsid; 
        unsigned long int f_fsid;
        int __f_unused;
        %rename(flag) f_flag;
        unsigned long int f_flag;
        %rename(namemax) f_namemax;
        unsigned long int f_namemax;
//    int __f_spare[6];
};
  
/* Definitions for the flag in `f_flag' (GNU C Library) */
%include "enums.swg"
%javaconst(1);
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


/* FUSE: fuse_file_info */
%rename(FileInfo) fuse_file_info;
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
%rename(Stat) stat;
struct stat {
        %rename(dev) st_dev;
        __dev_t st_dev;
	%immutable;
	unsigned short int __pad1;
	%mutable;
        %rename(_ino) __st_ino;
        __ino_t __st_ino;
        %rename(mode) st_mode;
        __mode_t st_mode;
        %rename(nlink) st_nlink;
        __nlink_t st_nlink;
        %rename(uid) st_uid;
        __uid_t st_uid;
        %rename(gid) st_gid;
        __gid_t st_gid;
        %rename(rdev) st_rdev;
        __dev_t st_rdev;
	%immutable;
        unsigned short int __pad2;
	%mutable;
        %rename(size) st_size;
        __off64_t st_size;
        %rename(blksize) st_blksize;
        __blksize_t st_blksize;
        %rename(blocks) st_blocks;
        __blkcnt64_t st_blocks;
        %rename(atim) st_atim;
        struct timespec st_atim;
        %rename(mtim) st_mtim;
        struct timespec st_mtim;
        %rename(ctim) st_ctim;
        struct timespec st_ctim;
        %rename(ino) st_ino;
          __ino64_t st_ino;
};

/* timespect (GNU C Library) [cpp -D_FILE_OFFSET_BITS=64 /usr/include/time.h] */
%rename(Timespec) timespec;
struct timespec {
        %rename(sec) tv_sec;
        __time_t tv_sec;            /* Seconds.  */
        %rename(nsec) tv_nsec;
        long int tv_nsec;           /* Nanoseconds.  */
};


/* iovec (GNU C Library) [cpp -D_FILE_OFFSET_BITS=64 /usr/include/sys/uio.h] */
%apply void* BUFF {void* iov_base}
struct iovec {
        %rename(base) iov_base;
        void *iov_base;
        %rename(len) iov_len;
        size_t iov_len;
};


%rename(FuseArgs) fuse_args;
struct fuse_args {
        /* Argument count */
        int argc;
        
        /* Argument vector.  NULL terminated */
//        %apply char* CBUFF {char** argv};
        char **argv;

        /* Is 'argv' allocated? */
        int allocated;
};

%rename(EntryParam) fuse_entry_param;
struct fuse_entry_param {
        fuse_ino_t ino;
        unsigned long generation;
        struct stat attr;
        double attr_timeout;
        double entry_timeout;
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


/* file mode bits from stat.h */
#define S_IFDIR       0040000 /* Directory.  */
#define S_IFCHR       0020000 /* Character device.  */
#define S_IFBLK       0060000 /* Block device.  */
#define S_IFREG       0100000 /* Regular file.  */
#define S_IFIFO       0010000 /* FIFO.  */
#define S_IFLNK       0120000 /* Symbolic link.  */
#define S_IFSOCK      0140000 /* Socket.  */
#define S_ISUID       04000   /* Set user ID on execution.  */
#define S_ISGID       02000   /* Set group ID on execution.  */
#define S_ISVTX       01000   /* Save swapped text after use (sticky).  */
#define S_IREAD       0400    /* Read by owner.  */
#define S_IWRITE      0200    /* Write by owner.  */
#define S_IEXEC       0100    /* Execute by owner.  */

/* errno.h (linux) [asm-generic/errno.h] */
#define EPERM            1      /* Operation not permitted */
#define ENOENT           2      /* No such file or directory */
#define ESRCH            3      /* No such process */
#define EINTR            4      /* Interrupted system call */
#define EIO              5      /* I/O error */
#define ENXIO            6      /* No such device or address */
#define E2BIG            7      /* Argument list too long */
#define ENOEXEC          8      /* Exec format error */
#define EBADF            9      /* Bad file number */
#define ECHILD          10      /* No child processes */
#define EAGAIN          11      /* Try again */
#define ENOMEM          12      /* Out of memory */
#define EACCES          13      /* Permission denied */
#define EFAULT          14      /* Bad address */
#define ENOTBLK         15      /* Block device required */
#define EBUSY           16      /* Device or resource busy */
#define EEXIST          17      /* File exists */
#define EXDEV           18      /* Cross-device link */
#define ENODEV          19      /* No such device */
#define ENOTDIR         20      /* Not a directory */
#define EISDIR          21      /* Is a directory */
#define EINVAL          22      /* Invalid argument */
#define ENFILE          23      /* File table overflow */
#define EMFILE          24      /* Too many open files */
#define ENOTTY          25      /* Not a typewriter */
#define ETXTBSY         26      /* Text file busy */
#define EFBIG           27      /* File too large */
#define ENOSPC          28      /* No space left on device */
#define ESPIPE          29      /* Illegal seek */
#define EROFS           30      /* Read-only file system */
#define EMLINK          31      /* Too many links */
#define EPIPE           32      /* Broken pipe */
#define EDOM            33      /* Math argument out of domain of func */
#define ERANGE          34      /* Math result not representable */

#define EDEADLK         35      /* Resource deadlock would occur */
#define ENAMETOOLONG    36      /* File name too long */
#define ENOLCK          37      /* No record locks available */
#define ENOSYS          38      /* Function not implemented */
#define ENOTEMPTY       39      /* Directory not empty */
#define ELOOP           40      /* Too many symbolic links encountered */
#define EWOULDBLOCK     EAGAIN  /* Operation would block */
#define ENOMSG          42      /* No message of desired type */
#define EIDRM           43      /* Identifier removed */
#define ECHRNG          44      /* Channel number out of range */
#define EL2NSYNC        45      /* Level 2 not synchronized */
#define EL3HLT          46      /* Level 3 halted */
#define EL3RST          47      /* Level 3 reset */
#define ELNRNG          48      /* Link number out of range */
#define EUNATCH         49      /* Protocol driver not attached */
#define ENOCSI          50      /* No CSI structure available */
#define EL2HLT          51      /* Level 2 halted */
#define EBADE           52      /* Invalid exchange */
#define EBADR           53      /* Invalid request descriptor */
#define EXFULL          54      /* Exchange full */
#define ENOANO          55      /* No anode */
#define EBADRQC         56      /* Invalid request code */
#define EBADSLT         57      /* Invalid slot */
#define EDEADLOCK       EDEADLK
#define EBFONT          59      /* Bad font file format */
#define ENOSTR          60      /* Device not a stream */
#define ENODATA         61      /* No data available */
#define ETIME           62      /* Timer expired */
#define ENOSR           63      /* Out of streams resources */
#define ENONET          64      /* Machine is not on the network */
#define ENOPKG          65      /* Package not installed */
#define EREMOTE         66      /* Object is remote */
#define ENOLINK         67      /* Link has been severed */
#define EADV            68      /* Advertise error */
#define ESRMNT          69      /* Srmount error */
#define ECOMM           70      /* Communication error on send */
#define EPROTO          71      /* Protocol error */
#define EMULTIHOP       72      /* Multihop attempted */
#define EDOTDOT         73      /* RFS specific error */
#define EBADMSG         74      /* Not a data message */
#define EOVERFLOW       75      /* Value too large for defined data type */
#define ENOTUNIQ        76      /* Name not unique on network */
#define EBADFD          77      /* File descriptor in bad state */
#define EREMCHG         78      /* Remote address changed */
#define ELIBACC         79      /* Can not access a needed shared library */
#define ELIBBAD         80      /* Accessing a corrupted shared library */
#define ELIBSCN         81      /* .lib section in a.out corrupted */
#define ELIBMAX         82      /* Attempting to link in too many shared libraries */
#define ELIBEXEC        83      /* Cannot exec a shared library directly */
#define EILSEQ          84      /* Illegal byte sequence */
#define ERESTART        85      /* Interrupted system call should be restarted */
#define ESTRPIPE        86      /* Streams pipe error */
#define EUSERS          87      /* Too many users */
#define ENOTSOCK        88      /* Socket operation on non-socket */
#define EDESTADDRREQ    89      /* Destination address required */
#define EMSGSIZE        90      /* Message too long */
#define EPROTOTYPE      91      /* Protocol wrong type for socket */
#define ENOPROTOOPT     92      /* Protocol not available */
#define EPROTONOSUPPORT 93      /* Protocol not supported */
#define ESOCKTNOSUPPORT 94      /* Socket type not supported */
#define EOPNOTSUPP      95      /* Operation not supported on transport endpoint */
#define EPFNOSUPPORT    96      /* Protocol family not supported */
#define EAFNOSUPPORT    97      /* Address family not supported by protocol */
#define EADDRINUSE      98      /* Address already in use */
#define EADDRNOTAVAIL   99      /* Cannot assign requested address */
#define ENETDOWN        100     /* Network is down */
#define ENETUNREACH     101     /* Network is unreachable */
#define ENETRESET       102     /* Network dropped connection because of reset */
#define ECONNABORTED    103     /* Software caused connection abort */
#define ECONNRESET      104     /* Connection reset by peer */
#define ENOBUFS         105     /* No buffer space available */
#define EISCONN         106     /* Transport endpoint is already connected */
#define ENOTCONN        107     /* Transport endpoint is not connected */
#define ESHUTDOWN       108     /* Cannot send after transport endpoint shutdown */
#define ETOOMANYREFS    109     /* Too many references: cannot splice */
#define ETIMEDOUT       110     /* Connection timed out */
#define ECONNREFUSED    111     /* Connection refused */
#define EHOSTDOWN       112     /* Host is down */
#define EHOSTUNREACH    113     /* No route to host */
#define EALREADY        114     /* Operation already in progress */
#define EINPROGRESS     115     /* Operation now in progress */
#define ESTALE          116     /* Stale NFS file handle */
#define EUCLEAN         117     /* Structure needs cleaning */
#define ENOTNAM         118     /* Not a XENIX named type file */
#define ENAVAIL         119     /* No XENIX semaphores available */
#define EISNAM          120     /* Is a named type file */
#define EREMOTEIO       121     /* Remote I/O error */
#define EDQUOT          122     /* Quota exceeded */
#define ENOMEDIUM       123     /* No medium found */
#define EMEDIUMTYPE     124     /* Wrong medium type */
#define ECANCELED       125     /* Operation Canceled */
#define ENOKEY          126     /* Required key not available */
#define EKEYEXPIRED     127     /* Key has expired */
#define EKEYREVOKED     128     /* Key has been revoked */
#define EKEYREJECTED    129     /* Key was rejected by service */
#define EOWNERDEAD      130     /* Owner died */
#define ENOTRECOVERABLE 131     /* State not recoverable */
#define ERFKILL         132     /* Operation not possible due to RF-kill */


