/**
 * Data structure class to hold the mapping to 
 * the implemantations of jlowfuse async tasks
 */

package jlowfuse.async;

import jlowfuse.async.tasks.*;

public class DefaultTaskImplementations<CTX extends Context> extends TaskImplementations<CTX> {
	private final static String TASK_PACKAGE = "jlowfuse.async.tasks";
	
	public Class<? extends Init<CTX>> initImpl = getDefaultImpl("Init"); 
	public Class<? extends Destroy<CTX>> destroyImpl = getDefaultImpl("Destroy");
	public Class<? extends Lookup<CTX>> lookupImpl = getDefaultImpl("Lookup");
	public Class<? extends Forget<CTX>> forgetImpl = getDefaultImpl("Forget");
	public Class<? extends Getattr<CTX>> getattrImpl = getDefaultImpl("Getattr");
	public Class<? extends Setattr<CTX>> setattrImpl = getDefaultImpl("Setattr");
	public Class<? extends Readlink<CTX>> readlinkImpl = getDefaultImpl("Readlink");
	public Class<? extends Mknod<CTX>> mknodImpl = getDefaultImpl("Mknod");
	public Class<? extends Mkdir<CTX>> mkdirImpl = getDefaultImpl("Mkdir");
	public Class<? extends Unlink<CTX>> unlinkImpl = getDefaultImpl("Unlink");
	public Class<? extends Rmdir<CTX>> rmdirImpl = getDefaultImpl("Rmdir");
	public Class<? extends Symlink<CTX>> symlinkImpl = getDefaultImpl("Symlink");
	public Class<? extends Rename<CTX>> renameImpl = getDefaultImpl("Rename");
	public Class<? extends Link<CTX>> linkImpl = getDefaultImpl("Link");
	public Class<? extends Open<CTX>> openImpl = getDefaultImpl("Open");
	public Class<? extends Read<CTX>> readImpl = getDefaultImpl("Read");
	public Class<? extends Write<CTX>> writeImpl = getDefaultImpl("Write");
	public Class<? extends Flush<CTX>> flushImpl = getDefaultImpl("Flush");
	public Class<? extends Release<CTX>> releaseImpl = getDefaultImpl("Release");
	public Class<? extends Fsync<CTX>> fsyncImpl = getDefaultImpl("Fsync");
	public Class<? extends Opendir<CTX>> opendirImpl = getDefaultImpl("Opendir");
	public Class<? extends Readdir<CTX>> readdirImpl = getDefaultImpl("Readdir");
	public Class<? extends Releasedir<CTX>> releasedirImpl = getDefaultImpl("Releasedir");
	public Class<? extends Fsyncdir<CTX>> fsyncdirImpl = getDefaultImpl("Fsyncdir");
	public Class<? extends Statfs<CTX>> statfsImpl = getDefaultImpl("Statfs");
	public Class<? extends Setxattr<CTX>> setxattrImpl = getDefaultImpl("Setxattr");
	public Class<? extends Getxattr<CTX>> getxattrImpl = getDefaultImpl("Getxattr");
	public Class<? extends Listxattr<CTX>> listxattrImpl = getDefaultImpl("Listxattr");
	public Class<? extends Removexattr<CTX>> removexattrImpl = getDefaultImpl("Removexattr");
	public Class<? extends Access<CTX>> accessImpl = getDefaultImpl("Access");
	public Class<? extends Create<CTX>> createImpl = getDefaultImpl("Create");
	public Class<? extends Bmap<CTX>> bmapImpl = getDefaultImpl("Bmap");
	
	public static <T extends JLowFuseTask<? extends Context>> Class<T> getDefaultImpl(String name) {
		return getImpl(TASK_PACKAGE + "." + name);
	}

}
