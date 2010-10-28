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
%include "various.i"
%include "buffers.i"

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
%rename(IoVec) iovec;
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
