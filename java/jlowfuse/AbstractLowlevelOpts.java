/**
 * Abstract base class for lowlevel fuse operations
 *
 * @author Marcel Lauhoff <ml@irq0.org>
 * 
 */ 

package jlowfuse;

import fuse.stat;

import java.nio.ByteBuffer;


public abstract class AbstractLowlevelOpts {
    public void init(ByteBuffer data) {
        return;
    }

    void destroy(ByteBuffer data) {
        return;
    }

    void lookup(FuseReq req, long parent, String name) {
        return;
    }

    void forget(FuseReq req, long ino, long nlookup) {
        return;
    }

    void getattr(FuseReq req, long ino) {
        return;
    }

    void setattr(FuseReq req, long ino, stat stat, int to_set) {
        return;
    }

    void readlink(FuseReq req, long ino) {
        return;
    }

    void mknod(FuseReq req, long parent, String name, short mode, short rdev) {
        return;
    }

    void mkdir(FuseReq req, long parent, String name, short mode) {
        return;
    }

    void unlink(FuseReq req, long parent, String name) {
        return;
    }

    void rmdir(FuseReq req, long parent, String name) {
        return;
    }

    void symlink(FuseReq req, String link, long parent, String name) {
        return;
    }

    void rename(FuseReq req, long parent, String name,
                long newparent, String newname) {
        return;
    }

    void link(FuseReq req, long ino, long newparent, String newname) {
        return;
    }

    void open(FuseReq req, long ino) {
        return;
    }

    void read(FuseReq req, long ino, int size, int off) {
        return;
    }

    void write(FuseReq req, long ino, ByteBuffer buf, int size, int off) {
        return;
    }

    void flush(FuseReq req, long ino) {
        return;
    }

    void release(FuseReq req, long ino) {
        return;
    }

    void fsync(FuseReq req, long ino, int datasync) {
        return;
    }

    void opendir(FuseReq req, long ino) {
        return;
    }

    void readdir(FuseReq req, long ino, int size, int off) {
        return;
    }

    void releasedir(FuseReq req, long ino) {
        return;
    }

    void fsyncdir(FuseReq req, long ino, int datasync) {
        return;
    }

    public void statfs(FuseReq req, long ino) {
        return;
    }

    void setxattr(FuseReq req, long ino, String name,
                  ByteBuffer value, int size, int flags) {
        return;
    }

    void getxattr(FuseReq req, long ino, String name, int size) {
        return;
    }

    void listxattr(FuseReq req, long ino, int size) {
        return;
    }

    void removexattr(FuseReq req, long ino, String name) {
        return;
    }

    void access(FuseReq req, long ino, int mask) {
        return;
    }

    void create(FuseReq req, long parent, String name, short mode) {
        return;
    }

    /*    
    void getlk(long ino, Flock lock) {
    }

    void setlk(long ino, Flock lock, int sleep) {
    }
    */

    void bmap(FuseReq req, long ino, int blocksize, long idx) {
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

