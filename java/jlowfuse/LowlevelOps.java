/**
 * Interface: Lowlevel Operations
 *
 * @author Marcel Lauhoff <ml@irq0.org>
 */

package jlowfuse;

import fuse.stat;
import fuse.fuse_file_info;
import java.nio.ByteBuffer;

public interface LowlevelOps {
    public void init();
    public void destroy();
    public void lookup(FuseReq req, long parent, String name);
    public void forget(FuseReq req, long ino, long nlookup);
    public void getattr(FuseReq req, long ino, fuse_file_info fi);
    public void setattr(FuseReq req, long ino, stat attr, int to_set, fuse_file_info fi);
    public void readlink(FuseReq req, long ino);
    public void mknod(FuseReq req, long parent, String name, short mode, short rdev);
    public void mkdir(FuseReq req, long parent, String name, short mode);
    public void unlink(FuseReq req, long parent, String name);
    public void rmdir(FuseReq req, long parent, String name);
    public void symlink(FuseReq req, String link, long parent, String name);
    public void rename(FuseReq req, long parent, String name,
                long newparent, String newname);
    public void link(FuseReq req, long ino, long newparent, String newname);
    public void open(FuseReq req, long ino, fuse_file_info fi);
    public void read(FuseReq req, long ino, int size, int off, fuse_file_info fi);
    public void write(FuseReq req, long ino, ByteBuffer buf, int off, fuse_file_info fi);
    public void flush(FuseReq req, long ino, fuse_file_info fi);
    public void release(FuseReq req, long ino, fuse_file_info fi);
    public void fsync(FuseReq req, long ino, int datasync, fuse_file_info fi);
    public void opendir(FuseReq req, long ino, fuse_file_info fi);
    public void readdir(FuseReq req, long ino, int size, int off, fuse_file_info fi);
    public void releasedir(FuseReq req, long ino, fuse_file_info fi);
    public void fsyncdir(FuseReq req, long ino, int datasync, fuse_file_info fi);
    public void statfs(FuseReq req, long ino);
    public void setxattr(FuseReq req, long ino, String name,
                  ByteBuffer value, int flags);
    public void getxattr(FuseReq req, long ino, String name, int size);
    public void listxattr(FuseReq req, long ino, int size);
    public void removexattr(FuseReq req, long ino, String name);
    public void access(FuseReq req, long ino, int mask);
    public void create(FuseReq req, long parent, String name, short mode);
    /*    
    public void getlk(long ino, Flock lock);
    public void setlk(long ino, Flock lock, int sleep);
    */
    public void bmap(FuseReq req, long ino, int blocksize, long idx);

    /*
    public void ioctl(long ino, int cmd, ByteBuffer arg);
    */

    /*
    public void poll(long ino, PollHandle ph);
    */
}
