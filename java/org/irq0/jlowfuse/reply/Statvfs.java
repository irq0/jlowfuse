package org.irq0.jlowfuse.reply;

public class Statvfs {
    // taken from freebsds sys/statvfs.h
    public static int F_BAVAIL = 0;       /* Number of blocks */
    public static int F_BFREE = 1;
    public static int F_BLOCKS = 2;
    public static int F_FAVAIL = 3;       /* Number of files (e.g., inodes) */
    public static int F_FFREE = 4;
    public static int F_FILES = 5;
    public static int F_BSIZE = 6;        /* Size of blocks counted above */
    public static int F_FLAG = 7;
    public static int F_FRSIZE = 8;       /* Size of fragments */
    public static int F_FSID = 9;         /* Not meaningful */
    public static int F_NAMEMAX = 10;      /* Same as pathconf(_PC_NAME_MAX) */

    private long[] arr;

    public void setBlocksAvail(long val) { this.arr[F_BAVAIL] = val; }
    public void setBlocksFree(long val) { this.arr[F_BFREE] = val; }
    public void setBlocks(long val) { this.arr[F_BLOCKS] = val; }
    public void setFilesAvail(long val) { this.arr[F_FAVAIL] = val; }
    public void setFilesFree(long val) { this.arr[F_FFREE] = val; }
    public void setFiles(long val) { this.arr[F_FILES] = val; }
    public void setBlockSize(long val) { this.arr[F_BSIZE] = val; }
    public void setFlag(long val) { this.arr[F_FLAG] = val; }
    public void setFrSize(long val) { this.arr[F_FRSIZE] = val; }
    public void setNameMax(long val) { this.arr[F_NAMEMAX] = val; }
}
