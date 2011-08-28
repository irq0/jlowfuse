/**
 * Proxy class for FUSE Operations
 * The c ops functions call this methods which then perform type wrapping and
 * call the methods in the `LowlevelOps` class.
 *
 * @author Marcel Lauhoff <ml@irq0.org>
 * 
 */ 

package jlowfuse.async;

import java.nio.ByteBuffer;

import jlowfuse.OpsProxy;

public class AsyncTasksOpsProxy implements OpsProxy {
    public void init(long data, long conn) {
	    
    }

    public void destroy(long data) {
    }

    public void lookup(long req, long parent, String name) {
    }

    public void forget(long req, long ino, long nlookup) {
    }

    public void getattr(long req, long ino, long fi) {
    }

    public void setattr(long req, long ino, long attr, int to_set, long fi) {
    }

    public void readlink(long req, long ino) {
    }

    public void mknod(long req, long parent, String name, short mode, short rdev) {
    }

    public void mkdir(long req, long parent, String name, short mode) {
    }

    public void unlink(long req, long parent, String name) {
    }

    public void rmdir(long req, long parent, String name) {
        
    }

    public void symlink(long req, String link, long parent, String name) {
        
    }

    public void rename(long req, long parent, String name, long newparent, String newname) {

                       
    }

    public void link(long req, long ino, long newparent, String newname) {
        
    }

    public void open(long req, long ino, long fi) {
        
    }

    public void read(long req, long ino, long size, long off, long fi) {
        
    }

    public void write(long req, long ino, ByteBuffer buf, long size, long off, long fi) {
        
    }

    public void flush(long req, long ino, long fi) {
        
    }

    public void release(long req, long ino, long fi) {
        
    }

    public void fsync(long req, long ino, int datasync, long fi) {
        
    }

    public void opendir(long req, long ino, long fi) {
        
    }

    public void readdir(long req, long ino, long size, long off, long fi) {
        
    }

    public void releasedir(long req, long ino, long fi) {
        
    }

    public void fsyncdir(long req, long ino, int datasync, long fi) {
        
    }

    public void statfs(long req, long ino) {
        
    }

    public void setxattr(long req, long ino, String name, ByteBuffer value, int size, int flags) {
        
    }

    public void getxattr(long req, long ino, String name, int size) {
        
    }

    public void listxattr(long req, long ino, int size) {
        
    }

    public void removexattr(long req, long ino, String name) {
        
    }

    public void access(long req, long ino, int mask) {
        
    }

	public void create(long req, long parent, String name, short mode, long fi) {
		
    }

	public void bmap(long req, long ino, int blocksize, long idx) {
        
    }
}

