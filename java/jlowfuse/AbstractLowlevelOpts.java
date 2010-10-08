/**
 * Abstract base class for lowlevel fuse operations
 *
 * @author Marcel Lauhoff <ml@irq0.org>
 * 
 */ 

package jlowfuse;

import java.nio.ByteBuffer;


public abstract class AbstractLowlevelOpts {
    public void init(ByteBuffer data) {
        return;
    }

    void destroy(ByteBuffer data) {
        return;
    }

    void lookup(long parent, String name) {
        return;
    }

    void forget(long ino, long nlookup) {
        return;
    }

    void getattr(long ino) {
        return;
    }

    void setattr(long ino, Stat stat, int to_set) {
        return;
    }

    void readlink(long ino) {
        return;
    }

    void mknod(long parent, String name, short mode, short rdev) {
        return;
    }

    void mkdir(long parent, String name, short mode) {
        return;
    }

    void unlink(long parent, String name) {
        return;
    }

    void rmdir(long parent, String name) {
        return;
    }

    void symlink(String link, long parent, String name) {
        return;
    }

    void rename(long parent, String name, long newparent, String newname) {
        return;
    }

    void link(long ino, long newparent, String newname) {
        return;
    }

    void open(long ino) {
        return;
    }

    void read(long ino, int size, int off) {
        return;
    }

    void write(long ino, ByteBuffer buf, int size, int off) {
        return;
    }

    void flush(long ino) {
        return;
    }

    void release(long ino) {
        return;
    }

    void fsync(long ino, int datasync) {
        return;
    }

    void opendir(long ino) {
        return;
    }

    void readdir(long ino, int size, int off) {
        return;
    }

    void releasedir(long ino) {
        return;
    }

    void fsyncdir(long ino, int datasync) {
        return;
    }

    public void statfs(long ino) {
        return;
    }

    void setxattr(long ino, String name, ByteBuffer value, int size,
                   int flags) {
        return;
    }

    void getxattr(long ino, String name, int size) {
        return;
    }

    void listxattr(long ino, int size) {
        return;
    }

    void removexattr(long ino, String name) {
        return;
    }

    void access(long ino, int mask) {
        return;
    }

    void create(long parent, String name, short mode) {
        return;
    }

    /*    
    void getlk(long ino, Flock lock) {
    }

    void setlk(long ino, Flock lock, int sleep) {
    }
    */

    void bmap(long ino, int blocksize, long idx) {
        return;
    }

    /*
    void ioctl(long ino, int cmd, ByteBuffer arg,
    */

    /*
    void poll(long ino, PollHandle ph) {
    }
    */
}