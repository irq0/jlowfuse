/**
 * Data structure class to hold the mapping to 
 * the implemantations of jlowfuse async tasks
 */

package jlowfuse.async;

import jlowfuse.async.tasks.*;

public class DefaultTaskImplementations<CTX extends Context> {
	private final static String TASK_PACKAGE = "jlowfuse.async.tasks";
	
	public Class<? extends Init<CTX>> initImpl = getImpl2("Init"); 
	public Class<? extends Destroy<CTX>> destroyImpl = getImpl2("Destroy");
	public Class<? extends Lookup<CTX>> lookupImpl = getImpl2("Lookup");
	public Class<? extends Forget<CTX>> forgetImpl = getImpl2("Destroy");
	public Class<? extends Getattr<CTX>> getattrImpl = getImpl2("Destroy");
	public Class<? extends Setattr<CTX>> setattrImpl = getImpl2("Destroy");
	public Class<? extends Readlink<CTX>> readlinkImpl = getImpl2("Destroy");
	public Class<? extends Mknod<CTX>> mknodImpl = getImpl2("Destroy");
	public Class<? extends Mkdir<CTX>> mkdirImpl = getImpl2("Destroy");
	public Class<? extends Unlink<CTX>> unlinkImpl = getImpl2("Destroy");
	public Class<? extends Rmdir<CTX>> rmdirImpl = getImpl2("Destroy");
	public Class<? extends Symlink<CTX>> symlinkImpl = getImpl2("Destroy");
	public Class<? extends Rename<CTX>> renameImpl = getImpl2("Destroy");
	public Class<? extends Link<CTX>> linkImpl = getImpl2("Destroy");
	public Class<? extends Open<CTX>> openImpl = getImpl2("Destroy");
	public Class<? extends Read<CTX>> readImpl = getImpl2("Destroy");
	public Class<? extends Write<CTX>> writeImpl = getImpl2("Destroy");
	public Class<? extends Flush<CTX>> flushImpl = getImpl2("Destroy");
	public Class<? extends Release<CTX>> releaseImpl = getImpl2("Destroy");
	public Class<? extends Fsync<CTX>> fsyncImpl = getImpl2("Destroy");
	public Class<? extends Opendir<CTX>> opendirImpl = getImpl2("Destroy");
	public Class<? extends Readdir<CTX>> readdirImpl = getImpl2("Destroy");
	public Class<? extends Releasedir<CTX>> releasedirImpl = getImpl2("Destroy");
	public Class<? extends Fsyncdir<CTX>> fsyncdirImpl = getImpl2("Destroy");
	public Class<? extends Statfs<CTX>> statfsImpl = getImpl2("Destroy");
	public Class<? extends Setxattr<CTX>> setxattrImpl = getImpl2("Destroy");
	public Class<? extends Getxattr<CTX>> getxattrImpl = getImpl2("Destroy");
	public Class<? extends Listxattr<CTX>> listxattrImpl = getImpl2("Destroy");
	public Class<? extends Removexattr<CTX>> removexattrImpl = getImpl2("Destroy");
	public Class<? extends Access<CTX>> accessImpl = getImpl2("Destroy");
	public Class<? extends Create<CTX>> createImpl = getImpl2("Destroy");
	public Class<? extends Bmap<CTX>> bmapImpl = getImpl2("Destroy");
	
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
	@SuppressWarnings("unchecked")
	private static <T extends JLowFuseTask<? extends Context>> Class<T> getImpl2(String name) {	
		try {
			Class<T> result = (Class<T>) Class.forName(TASK_PACKAGE + "." + name);			
			return result;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Should not happen");
		}				
	}

}
