/**
 * Converts lowlevel FUSE method calls to asyncronous tasks 
 *
 * @author Marcel Lauhoff <ml@irq0.org>
 */ 

package jlowfuse.async;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;

import fuse.FileInfo;
import fuse.Stat;
import jlowfuse.FuseReq;
import jlowfuse.LowlevelOps;
import jlowfuse.async.tasks.Init;
import jlowfuse.async.tasks.JLowFuseTask;

public class AsyncLowlevelOps implements LowlevelOps {
	protected DefaultTaskImplementations taskImplementations;
	protected ExecutorService executor;
	
	public AsyncLowlevelOps(DefaultTaskImplementations taskImplementations, ExecutorService executor) {
		this.taskImplementations = taskImplementations; 
		this.executor = executor;
	}
	
	@Override
    public void init() {
		JLowFuseTask task;
        try {
	        task = taskImplementations.initImpl.newInstance();
	        executor.submit(task);
        } catch (InstantiationException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        } catch (IllegalAccessException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }		
    }

	@Override
    public void destroy() {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void lookup(FuseReq req, long parent, String name) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void forget(FuseReq req, long ino, long nlookup) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void getattr(FuseReq req, long ino, FileInfo fi) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void setattr(FuseReq req, long ino, Stat attr, int to_set,
            FileInfo fi) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void readlink(FuseReq req, long ino) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void mknod(FuseReq req, long parent, String name, short mode,
            short rdev) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void mkdir(FuseReq req, long parent, String name, short mode) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void unlink(FuseReq req, long parent, String name) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void rmdir(FuseReq req, long parent, String name) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void symlink(FuseReq req, String link, long parent, String name) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void rename(FuseReq req, long parent, String name, long newparent,
            String newname) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void link(FuseReq req, long ino, long newparent, String newname) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void open(FuseReq req, long ino, FileInfo fi) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void read(FuseReq req, long ino, long size, long off, FileInfo fi) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void write(FuseReq req, long ino, ByteBuffer buf, long off,
            FileInfo fi) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void flush(FuseReq req, long ino, FileInfo fi) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void release(FuseReq req, long ino, FileInfo fi) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void fsync(FuseReq req, long ino, int datasync, FileInfo fi) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void opendir(FuseReq req, long ino, FileInfo fi) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void readdir(FuseReq req, long ino, long size, long off, FileInfo fi) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void releasedir(FuseReq req, long ino, FileInfo fi) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void fsyncdir(FuseReq req, long ino, int datasync, FileInfo fi) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void statfs(FuseReq req, long ino) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void setxattr(FuseReq req, long ino, String name, ByteBuffer value,
            int flags) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void getxattr(FuseReq req, long ino, String name, int size) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void listxattr(FuseReq req, long ino, int size) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void removexattr(FuseReq req, long ino, String name) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void access(FuseReq req, long ino, int mask) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void create(FuseReq req, long parent, String name, short mode,
            FileInfo fi) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void bmap(FuseReq req, long ino, int blocksize, long idx) {
	    // TODO Auto-generated method stub
	    
    }
}

