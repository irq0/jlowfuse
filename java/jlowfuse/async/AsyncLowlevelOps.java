/**
 * Converts lowlevel FUSE method calls to asyncronous tasks 
 *
 * @author Marcel Lauhoff <ml@irq0.org>
 */ 

package jlowfuse.async;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;

import fuse.FileInfo;
import fuse.Stat;
import jlowfuse.FuseReq;
import jlowfuse.LowlevelOps;
import jlowfuse.async.tasks.Init;
import jlowfuse.async.tasks.JLowFuseTask;

public class AsyncLowlevelOps<CTX extends Context> implements LowlevelOps {
	protected DefaultTaskImplementations taskImplementations;
	protected ExecutorService executor;
	protected CTX context;
	
	public AsyncLowlevelOps(DefaultTaskImplementations taskImplementations, ExecutorService executor, CTX context) {
		this.taskImplementations = taskImplementations; 
		this.executor = executor;
		this.context = context;
	}
	
	/** Get constructor for the given task implementation */
	private Constructor<?> getTaskConstructor(Class<?> impl) {
		Constructor<?>[] c = impl.getConstructors();
		
		if (c.length > 1)
			throw new RuntimeException(String.format("Tasks %s may only have one constructur", impl.getName()));
		else if (c.length == 0)
			throw new RuntimeException(String.format("Task %s has no constructor ?!", impl.getName()));
		
		return c[0];
	}
	
	/** submit task to executor */
	private void submitTask(JLowFuseTask task) {
		executor.submit(task);
	}
	
	/** Create instance of task object */ 
	private JLowFuseTask instantiateTask(Constructor<?> constructor, Object ... arguments) {
		try {
	        JLowFuseTask task = (JLowFuseTask) constructor.newInstance(arguments);
	        return task;
	        
        } catch (IllegalArgumentException e) { /* should only happen if this class is implemented wrongly */
        	e.printStackTrace();
        	throw new RuntimeException(String.format("Incorrect constructor call in %s for class %s", this.getClass().getName(), constructor.getName()));
        } catch (InstantiationException e) {
        	e.printStackTrace();
        	throw new RuntimeException("Should not happen " + e.getMessage());
        } catch (IllegalAccessException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Should not happen " + e.getMessage());
        } catch (InvocationTargetException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Should not happen " + e.getMessage());
        }
	}
	
	/** Create, Initialize and Submit new Task */
	private void createAndSubmitTask(Class<?> impl, Object ... arguments) {
    	Constructor<?> c = getTaskConstructor(impl);
    	JLowFuseTask task = (Init) instantiateTask(c);
        task.initContext(this.context);
        submitTask(task);			
	}
	
	/* to many lines of booooring method-call to task object conversion code follows */
	
    public void init() {
    	createAndSubmitTask(taskImplementations.initImpl);
    }

    public void destroy() {
	  	createAndSubmitTask(taskImplementations.destroyImpl);	    
    }

    public void lookup(FuseReq req, long parent, String name) {
	  	createAndSubmitTask(taskImplementations.destroyImpl, req, parent, name);	    
    }

    public void forget(FuseReq req, long ino, long nlookup) {
	  	createAndSubmitTask(taskImplementations.forgetImpl, req, ino, nlookup);	    
    }

    public void getattr(FuseReq req, long ino, FileInfo fi) {
    	createAndSubmitTask(taskImplementations.getattrImpl, req, ino, fi);
    }

    public void setattr(FuseReq req, long ino, Stat attr, int to_set, FileInfo fi) {
    	createAndSubmitTask(taskImplementations.setattrImpl, req, ino, attr, to_set, fi);
    }

    public void readlink(FuseReq req, long ino) {
    	createAndSubmitTask(taskImplementations.readlinkImpl, req, ino);
    }
	
    public void mknod(FuseReq req, long parent, String name, short mode, short rdev) {
    	createAndSubmitTask(taskImplementations.mknodImpl, req, parent, name, mode, rdev);    
    }

	
    public void mkdir(FuseReq req, long parent, String name, short mode) {
    	createAndSubmitTask(taskImplementations.mkdirImpl, req, parent, name, mode);
    }

	
    public void unlink(FuseReq req, long parent, String name) {
    	createAndSubmitTask(taskImplementations.unlinkImpl, req, parent, name);
    }

	
    public void rmdir(FuseReq req, long parent, String name) {
    	createAndSubmitTask(taskImplementations.rmdirImpl, req, parent, name);
    }

	
    public void symlink(FuseReq req, String link, long parent, String name) {
    	createAndSubmitTask(taskImplementations.symlinkImpl, req, link, parent, name);
    }

	
    public void rename(FuseReq req, long parent, String name, long newparent, String newname) {
    	createAndSubmitTask(taskImplementations.renameImpl, req, parent, name, newparent, newname);
    }

	
    public void link(FuseReq req, long ino, long newparent, String newname) {
    	createAndSubmitTask(taskImplementations.linkImpl, req, ino, newparent, newname);
    }

	
    public void open(FuseReq req, long ino, FileInfo fi) {
    	createAndSubmitTask(taskImplementations.openImpl, req, ino, fi);
    }

	
    public void read(FuseReq req, long ino, long size, long off, FileInfo fi) {
    	createAndSubmitTask(taskImplementations.readImpl, ino, size, off, fi);
    }

	
    public void write(FuseReq req, long ino, ByteBuffer buf, long off, FileInfo fi) {
    	createAndSubmitTask(taskImplementations.writeImpl, req, ino, buf, off, fi);
    }

	
    public void flush(FuseReq req, long ino, FileInfo fi) {
    	createAndSubmitTask(taskImplementations.flushImpl, req, ino, fi);
    }

	
    public void release(FuseReq req, long ino, FileInfo fi) {
    	createAndSubmitTask(taskImplementations.releaseImpl, req, ino, fi);
    }

	
    public void fsync(FuseReq req, long ino, int datasync, FileInfo fi) {
    	createAndSubmitTask(taskImplementations.fsyncImpl, req, ino, datasync, fi);
    }

	
    public void opendir(FuseReq req, long ino, FileInfo fi) {
    	createAndSubmitTask(taskImplementations.opendirImpl, req, ino, fi);
    }

	
    public void readdir(FuseReq req, long ino, long size, long off, FileInfo fi) {
    	createAndSubmitTask(taskImplementations.readdirImpl, req, ino, size, off, fi);
    }

	
    public void releasedir(FuseReq req, long ino, FileInfo fi) {
    	createAndSubmitTask(taskImplementations.releasedirImpl, req, ino, fi);
    }

	
    public void fsyncdir(FuseReq req, long ino, int datasync, FileInfo fi) {
    	createAndSubmitTask(taskImplementations.fsyncdirImpl, req, ino, datasync, fi);
    }

	
    public void statfs(FuseReq req, long ino) {
    	createAndSubmitTask(taskImplementations.statfsImpl, req, ino);
    }

	
    public void setxattr(FuseReq req, long ino, String name, ByteBuffer value, int flags) {
    	createAndSubmitTask(taskImplementations.setxattrImpl, req, ino, name, value, flags);
    }

	
    public void getxattr(FuseReq req, long ino, String name, int size) {
    	createAndSubmitTask(taskImplementations.getxattrImpl, req, ino, name, size);
    }

	
    public void listxattr(FuseReq req, long ino, int size) {
    	createAndSubmitTask(taskImplementations.listxattrImpl, req, ino, size);
    }

	
    public void removexattr(FuseReq req, long ino, String name) {
    	createAndSubmitTask(taskImplementations.removexattrImpl, req, ino, name);
    }

	
    public void access(FuseReq req, long ino, int mask) {
    	createAndSubmitTask(taskImplementations.accessImpl, req, ino, mask);
    }

	
    public void create(FuseReq req, long parent, String name, short mode, FileInfo fi) {
    	createAndSubmitTask(taskImplementations.createImpl, req, parent, name, mode, fi);
    }

	
    public void bmap(FuseReq req, long ino, int blocksize, long idx) {
    	createAndSubmitTask(taskImplementations.bmapImpl, req, ino, blocksize, idx);
    }
}

