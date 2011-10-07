package jlowfuse.async;

import java.lang.reflect.Constructor;

import jlowfuse.async.tasks.*;

public class TaskImplementations<CTX extends Context> {
	public Class<? extends Init<CTX>> initImpl = null;
	public Class<? extends Destroy<CTX>> destroyImpl = null;
	public Class<? extends Lookup<CTX>> lookupImpl = null;
	public Class<? extends Forget<CTX>> forgetImpl = null;
	public Class<? extends Getattr<CTX>> getattrImpl = null;
	public Class<? extends Setattr<CTX>> setattrImpl = null;
	public Class<? extends Readlink<CTX>> readlinkImpl = null;
	public Class<? extends Mknod<CTX>> mknodImpl = null;
	public Class<? extends Mkdir<CTX>> mkdirImpl = null;
	public Class<? extends Unlink<CTX>> unlinkImpl = null;
	public Class<? extends Rmdir<CTX>> rmdirImpl = null;
	public Class<? extends Symlink<CTX>> symlinkImpl = null;
	public Class<? extends Rename<CTX>> renameImpl = null;
	public Class<? extends Link<CTX>> linkImpl = null;
	public Class<? extends Open<CTX>> openImpl = null;
	public Class<? extends Read<CTX>> readImpl = null;
	public Class<? extends Write<CTX>> writeImpl = null;
	public Class<? extends Flush<CTX>> flushImpl = null;
	public Class<? extends Release<CTX>> releaseImpl = null;
	public Class<? extends Fsync<CTX>> fsyncImpl = null;
	public Class<? extends Opendir<CTX>> opendirImpl = null;
	public Class<? extends Readdir<CTX>> readdirImpl = null;
	public Class<? extends Releasedir<CTX>> releasedirImpl = null;
	public Class<? extends Fsyncdir<CTX>> fsyncdirImpl = null;
	public Class<? extends Statfs<CTX>> statfsImpl = null;
	public Class<? extends Setxattr<CTX>> setxattrImpl = null;
	public Class<? extends Getxattr<CTX>> getxattrImpl = null;
	public Class<? extends Listxattr<CTX>> listxattrImpl = null;
	public Class<? extends Removexattr<CTX>> removexattrImpl = null;
	public Class<? extends Access<CTX>> accessImpl = null;
	public Class<? extends Create<CTX>> createImpl = null;
	public Class<? extends Bmap<CTX>> bmapImpl = null;
	
	
	/*
	@SuppressWarnings("unchecked")
	private static Class<? extends JLowFuseTask<? extends Context>> getImpl(String name) {	
		try {
			Class<JLowFuseTask<? extends Context>> base =
				(Class<JLowFuseTask<? extends Context>>) Class.forName("jlowfuse.async.tasks.JLowFuseTask");

			Class<? extends JLowFuseTask<? extends Context>> result = 
				Class.forName(TASK_PACKAGE + "." + name).asSubclass(base);			
			return result;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Should not happen");
		}				
	}
	*/
	
	@SuppressWarnings("unchecked")
	public static <T extends JLowFuseTask<? extends Context>> Class<T> getImpl(String classname) {	
		try {
			Class<T> result = (Class<T>) Class.forName(classname);			
			return result;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Should not happen");
		}				
	}
	/** Get constructor for the given task implementation */
	@SuppressWarnings("unchecked")
	public static <CTX extends Context> Constructor<? extends JLowFuseTask<CTX>> getTaskConstructor(Class<? extends JLowFuseTask<CTX>> impl) {
		Constructor<? extends JLowFuseTask<CTX>>[] c = 
			(Constructor<? extends JLowFuseTask<CTX>>[]) impl.getConstructors();
		
		if (c.length > 1)
			throw new RuntimeException(String.format("Tasks %s may only have one constructur", impl.getName()));
		else if (c.length == 0)
			throw new RuntimeException(String.format("Task %s has no constructor ?!", impl.getName()));
		
		return c[0];
	}
}
