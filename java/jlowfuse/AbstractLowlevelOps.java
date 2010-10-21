/**
 * Abstract base class for lowlevel fuse operations
 *
 * @author Marcel Lauhoff <ml@irq0.org>
 * 
 */ 

package jlowfuse;

import fuse.stat;
import fuse.fuse;
import fuse.fuseConstants;
import fuse.fuse_file_info;
import java.nio.ByteBuffer;


public abstract class AbstractLowlevelOps implements LowlevelOps {
    public void init() {
    }

    public void destroy() {
    }

    public void lookup(FuseReq req, long parent, String name) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void forget(FuseReq req, long ino, long nlookup) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void getattr(FuseReq req, long ino, fuse_file_info fi) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void setattr(FuseReq req, long ino, stat attr, int to_set,
                        fuse_file_info fi) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void readlink(FuseReq req, long ino) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void mknod(FuseReq req, long parent, String name, short mode, short rdev) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void mkdir(FuseReq req, long parent, String name, short mode) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void unlink(FuseReq req, long parent, String name) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void rmdir(FuseReq req, long parent, String name) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void symlink(FuseReq req, String link, long parent, String name) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void rename(FuseReq req, long parent, String name,
                long newparent, String newname) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void link(FuseReq req, long ino, long newparent, String newname) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void open(FuseReq req, long ino, fuse_file_info fi) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void read(FuseReq req, long ino, int size, int off, fuse_file_info fi) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void write(FuseReq req, long ino, ByteBuffer buf, int off,
                      fuse_file_info fi) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void flush(FuseReq req, long ino, fuse_file_info fi) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void release(FuseReq req, long ino, fuse_file_info fi) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void fsync(FuseReq req, long ino, int datasync, fuse_file_info fi) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void opendir(FuseReq req, long ino, fuse_file_info fi) {
        fuse.fuse_reply_open(req, fi);
    }

    public void readdir(FuseReq req, long ino, int size, int off, fuse_file_info fi) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void releasedir(FuseReq req, long ino, fuse_file_info fi) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void fsyncdir(FuseReq req, long ino, int datasync,
                         fuse_file_info fi) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void statfs(FuseReq req, long ino) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void setxattr(FuseReq req, long ino, String name,
                  ByteBuffer value, int flags) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void getxattr(FuseReq req, long ino, String name, int size) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void listxattr(FuseReq req, long ino, int size) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void removexattr(FuseReq req, long ino, String name) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void access(FuseReq req, long ino, int mask) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    public void create(FuseReq req, long parent, String name, short mode) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    /*    
    public void getlk(long ino, Flock lock) {
    }

    public void setlk(long ino, Flock lock, int sleep) {
    }
    */

    public void bmap(FuseReq req, long ino, int blocksize, long idx) {
        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }

    /*
    public void ioctl(long ino, int cmd, ByteBuffer arg,
    */

    /*
    public void poll(long ino, PollHandle ph) {
    }
    */
}

