/**
 * Abstract base class for lowlevel fuse operations
 *
 * @author Marcel Lauhoff <ml@irq0.org>
 * 
 */ 

package org.irq0.jlowfuse;

import org.irq0.jlowfuse.reply.*;

import java.nio.ByteBuffer;


public abstract class AbstractLowlevelOpts {
    public void init(ByteBuffer data) {
        return;
    }

    void destroy(ByteBuffer data) {
        return;
    }

    Reply lookup(long parent, String name) {
        return null;
    }

    Reply forget(long ino, long nlookup) {
        return null;
    }

    Reply getattr(long ino) {
        return null;
    }

    Reply setattr(long ino, Stat stat, int to_set) {
        return null;
    }

    Reply readlink(long ino) {
        return null;
    }

    Reply mknod(long parent, String name, short mode, short rdev) {
        return null;
    }

    Reply mkdir(long parent, String name, short mode) {
        return null;
    }

    Reply unlink(long parent, String name) {
        return null;
    }

    Reply rmdir(long parent, String name) {
        return null;
    }

    Reply symlink(String link, long parent, String name) {
        return null;
    }

    Reply rename(long parent, String name, long newparent, String newname) {
        return null;
    }

    Reply link(long ino, long newparent, String newname) {
        return null;
    }

    Reply open(long ino) {
        return null;
    }

    Reply read(long ino, int size, int off) {
        return null;
    }

    Reply write(long ino, ByteBuffer buf, int size, int off) {
        return null;
    }

    Reply flush(long ino) {
        return null;
    }

    Reply release(long ino) {
        return null;
    }

    Reply fsync(long ino, int datasync) {
        return null;
    }

    Reply opendir(long ino) {
        return null;
    }

    Reply readdir(long ino, int size, int off) {
        return null;
    }

    Reply releasedir(long ino) {
        return null;
    }

    Reply fsyncdir(long ino, int datasync) {
        return null;
    }

    public Reply statfs(long ino) {
        return null;
    }

    Reply setxattr(long ino, String name, ByteBuffer value, int size,
                   int flags) {
        return null;
    }

    Reply getxattr(long ino, String name, int size) {
        return null;
    }

    Reply listxattr(long ino, int size) {
        return null;
    }

    Reply removexattr(long ino, String name) {
        return null;
    }

    Reply access(long ino, int mask) {
        return null;
    }

    Reply create(long parent, String name, short mode) {
        return null;
    }

    /*    
    Reply getlk(long ino, Flock lock) {
    }

    Reply setlk(long ino, Flock lock, int sleep) {
    }
    */

    Reply bmap(long ino, int blocksize, long idx) {
        return null;
    }

    /*
    Reply ioctl(long ino, int cmd, ByteBuffer arg,
    */

    /*
    Reply poll(long ino, PollHandle ph) {
    }
    */
}















