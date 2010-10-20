/**
 * Abstract base class for lowlevel fuse operations
 *
 * @author Marcel Lauhoff <ml@irq0.org>
 * 
 */ 

package jlowfuse;

import fuse.stat;

import java.nio.ByteBuffer;


public abstract class AbstractLowlevelOps {
    public void init() {
        return;
    }

    public void destroy() {
        return;
    }

    public void lookup(FuseReq req, long parent, String name) {
        return;
    }

    public void forget(FuseReq req, long ino, long nlookup) {
        return;
    }

    public void getattr(FuseReq req, long ino) {
        return;
    }

    public void setattr(FuseReq req, long ino, stat attr, int to_set) {
        return;
    }

    public void readlink(FuseReq req, long ino) {
        return;
    }

    public void mknod(FuseReq req, long parent, String name, short mode, short rdev) {
        return;
    }

    public void mkdir(FuseReq req, long parent, String name, short mode) {
        return;
    }

    public void unlink(FuseReq req, long parent, String name) {
        return;
    }

    public void rmdir(FuseReq req, long parent, String name) {
        return;
    }

    public void symlink(FuseReq req, String link, long parent, String name) {
        return;
    }

    public void rename(FuseReq req, long parent, String name,
                long newparent, String newname) {
        return;
    }

    public void link(FuseReq req, long ino, long newparent, String newname) {
        return;
    }

    public void open(FuseReq req, long ino) {
        return;
    }

    public void read(FuseReq req, long ino, int size, int off) {
        return;
    }

    public void write(FuseReq req, long ino, ByteBuffer buf, int off) {
        return;
    }

    public void flush(FuseReq req, long ino) {
        return;
    }

    public void release(FuseReq req, long ino) {
        return;
    }

    public void fsync(FuseReq req, long ino, int datasync) {
        return;
    }

    public void opendir(FuseReq req, long ino) {
        return;
    }

    public void readdir(FuseReq req, long ino, int size, int off) {
        return;
    }

    public void releasedir(FuseReq req, long ino) {
        return;
    }

    public void fsyncdir(FuseReq req, long ino, int datasync) {
        return;
    }

    public void statfs(FuseReq req, long ino) {
        return;
    }

    public void setxattr(FuseReq req, long ino, String name,
                  ByteBuffer value, int flags) {
        return;
    }

    public void getxattr(FuseReq req, long ino, String name, int size) {
        return;
    }

    public void listxattr(FuseReq req, long ino, int size) {
        return;
    }

    public void removexattr(FuseReq req, long ino, String name) {
        return;
    }

    public void access(FuseReq req, long ino, int mask) {
        return;
    }

    public void create(FuseReq req, long parent, String name, short mode) {
        return;
    }

    /*    
    public void getlk(long ino, Flock lock) {
    }

    public void setlk(long ino, Flock lock, int sleep) {
    }
    */

    public void bmap(FuseReq req, long ino, int blocksize, long idx) {
        return;
    }

    /*
    public void ioctl(long ino, int cmd, ByteBuffer arg,
    */

    /*
    public void poll(long ino, PollHandle ph) {
    }
    */
}

