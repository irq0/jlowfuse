package org.irq0.jlowfuse;

public class Errno {
    // unix errno.h tanken from FreeBSD 8.1
    public static final int EPERM           = 1;         /* Operation not permitted */
    public static final int ENOENT          = 2;         /* No such file or directory */
    public static final int ESRCH           = 3;         /* No such process */
    public static final int EINTR           = 4;         /* Interrupted system call */
    public static final int EIO             = 5;         /* Input/output error */
    public static final int ENXIO           = 6;         /* Device not configured */
    public static final int E2BIG           = 7;         /* Argument list too long */
    public static final int ENOEXEC         = 8;         /* Exec format error */
    public static final int EBADF           = 9;         /* Bad file descriptor */
    public static final int ECHILD          = 10;         /* No child processes */
    public static final int EDEADLK         = 11;         /* Resource deadlock avoided */
    public static final int ENOMEM          = 12;         /* Cannot allocate memory */
    public static final int EACCES          = 13;         /* Permission denied */
    public static final int EFAULT          = 14;         /* Bad address */
    public static final int ENOTBLK         = 15;         /* Block device required */
    public static final int EBUSY           = 16;         /* Device busy */
    public static final int EEXIST          = 17;         /* File exists */
    public static final int EXDEV           = 18;         /* Cross-device link */
    public static final int ENODEV          = 19;         /* Operation not supported by device */
    public static final int ENOTDIR         = 20;         /* Not a directory */
    public static final int EISDIR          = 21;         /* Is a directory */
    public static final int EINVAL          = 22;         /* Invalid argument */
    public static final int ENFILE          = 23;         /* Too many open files in system */
    public static final int EMFILE          = 24;         /* Too many open files */
    public static final int ENOTTY          = 25;         /* Inappropriate ioctl for device */
    public static final int ETXTBSY         = 26;         /* Text file busy */
    public static final int EFBIG           = 27;         /* File too large */
    public static final int ENOSPC          = 28;         /* No space left on device */
    public static final int ESPIPE          = 29;         /* Illegal seek */
    public static final int EROFS           = 30;         /* Read-only filesystem */
    public static final int EMLINK          = 31;         /* Too many links */
    public static final int EPIPE           = 32;         /* Broken pipe */
    /* math software */
    public static final int EDOM            = 33;         /* Numerical argument out of domain */
    public static final int ERANGE          = 34;         /* Result too large */
    /* non-blocking and interrupt i/o */ 
    public static final int EAGAIN          = 35;         /* Resource temporarily unavailable */
    public static final int EINPROGRESS     = 36;         /* Operation now in progress */
    public static final int EALREADY        = 37;         /* Operation already in progress */
    /* ipc/network software -- argument errors */ 
    public static final int ENOTSOCK        = 38;         /* Socket operation on non-socket */
    public static final int EDESTADDRREQ    = 39;         /* Destination address required */
    public static final int EMSGSIZE        = 40;         /* Message too long */
    public static final int EPROTOTYPE      = 41;         /* Protocol wrong type for socket */
    public static final int ENOPROTOOPT     = 42;         /* Protocol not available */
    public static final int EPROTONOSUPPORT = 43;         /* Protocol not supported */
    public static final int ESOCKTNOSUPPORT = 44;         /* Socket type not supported */
    public static final int EOPNOTSUPP      = 45;         /* Operation not supported */
    public static final int EPFNOSUPPORT    = 46;         /* Protocol family not supported */
    public static final int EAFNOSUPPORT    = 47;         /* Address family not supported by protocol family */
    public static final int EADDRINUSE      = 48;         /* Address already in use */
    public static final int EADDRNOTAVAIL   = 49;         /* Can't assign requested address */
    /* ipc/network software -- operational errors */
    public static final int ENETDOWN        = 50;         /* Network is down */
    public static final int ENETUNREACH     = 51;         /* Network is unreachable */
    public static final int ENETRESET       = 52;         /* Network dropped connection on reset */
    public static final int ECONNABORTED    = 53;         /* Software caused connection abort */
    public static final int ECONNRESET      = 54;         /* Connection reset by peer */
    public static final int ENOBUFS         = 55;         /* No buffer space available */
    public static final int EISCONN         = 56;         /* Socket is already connected */
    public static final int ENOTCONN        = 57;         /* Socket is not connected */
    public static final int ESHUTDOWN       = 58;         /* Can't send after socket shutdown */
    public static final int ETOOMANYREFS    = 59;         /* Too many references: can't splice */
    public static final int ETIMEDOUT       = 60;         /* Operation timed out */
    public static final int ECONNREFUSED    = 61;         /* Connection refused */
    public static final int ELOOP           = 62;         /* Too many levels of symbolic links */
    public static final int ENAMETOOLONG    = 63;         /* File name too long */
    /* should be rearranged */
    public static final int EHOSTDOWN       = 64;         /* Host is down */
    public static final int EHOSTUNREACH    = 65;         /* No route to host */
    public static final int ENOTEMPTY       = 66;         /* Directory not empty */
    /* quotas & mush */
    public static final int EPROCLIM        = 67;         /* Too many processes */
    public static final int EUSERS          = 68;         /* Too many users */
    public static final int EDQUOT          = 69;         /* Disc quota exceeded */
    /* Network File System */
    public static final int ESTALE          = 70;         /* Stale NFS file handle */
    public static final int EREMOTE         = 71;         /* Too many levels of remote in path */
    public static final int EBADRPC         = 72;         /* RPC struct is bad */
    public static final int ERPCMISMATCH    = 73;         /* RPC version wrong */
    public static final int EPROGUNAVAIL    = 74;         /* RPC prog. not avail */
    public static final int EPROGMISMATCH   = 75;         /* Program version wrong */
    public static final int EPROCUNAVAIL    = 76;         /* Bad procedure for program */
    public static final int ENOLCK          = 77;         /* No locks available */
    public static final int ENOSYS          = 78;         /* Function not implemented */
    public static final int EFTYPE          = 79;         /* Inappropriate file type or format */
    public static final int EAUTH           = 80;         /* Authentication error */
    public static final int ENEEDAUTH       = 81;         /* Need authenticator */
    public static final int EIDRM           = 82;         /* Identifier removed */
    public static final int ENOMSG          = 83;         /* No message of desired type */
    public static final int EOVERFLOW       = 84;         /* Value too large to be stored in data type */
    public static final int ECANCELED       = 85;         /* Operation canceled */
    public static final int EILSEQ          = 86;         /* Illegal byte sequence */
    public static final int ENOATTR         = 87;         /* Attribute not found */
    public static final int EDOOFUS         = 88;         /* Programming error */
    public static final int EBADMSG         = 89;         /* Bad message */
    public static final int EMULTIHOP       = 90;         /* Multihop attempted */
    public static final int ENOLINK         = 91;         /* Link has been severed */
    public static final int EPROTO          = 92;         /* Protocol error */
    public static final int ENOTCAPABLE     = 93;         /* Capabilities insufficient */
    public static final int ELAST           = 93;         /* Must be equal largest errno */ 
}