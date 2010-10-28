/* constants required by fuse */
%module StatConstants
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

/* file mode bits from stat.h */
enum {
	IFDIR  = 0040000,       /* Directory.  */
	IFCHR  = 0020000,       /* Character device.  */
	IFBLK  = 0060000,       /* Block device. */
	IFREG  = 0100000,       /* Regular file. */
	IFIFO  = 0010000,       /* FIFO. */
	IFLNK  = 0120000,       /* Symbolic link.  */
	IFSOCK = 0140000,       /* Socket.  */
	ISUID  = 04000,         /* Set user ID on execution.  */
	ISGID  = 02000,         /* Set group ID on execution.  */
	ISVTX  = 01000,         /* Save swapped text after use (sticky). */
	IREAD  = 0400,          /* Read by owner. */
	IWRITE = 0200,          /* Write by owner.  */
	IEXEC  = 0100,          /* Execute by owner.  */
};
