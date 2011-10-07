/**
 * Data structure class to hold the mapping to
 * the implemantations of jlowfuse async tasks
 */

package jlowfuse.async;

import jlowfuse.async.tasks.*;

public class DefaultTaskImplementations<CTX extends Context> extends TaskImplementations<CTX> {
	private final static String TASK_PACKAGE = "jlowfuse.async.tasks";

	public DefaultTaskImplementations() {
		initImpl = getDefaultImpl("Init");
		destroyImpl = getDefaultImpl("Destroy");
		lookupImpl = getDefaultImpl("Lookup");
		forgetImpl = getDefaultImpl("Forget");
		getattrImpl = getDefaultImpl("Getattr");
		setattrImpl = getDefaultImpl("Setattr");
		readlinkImpl = getDefaultImpl("Readlink");
		mknodImpl = getDefaultImpl("Mknod");
		mkdirImpl = getDefaultImpl("Mkdir");
		unlinkImpl = getDefaultImpl("Unlink");
		rmdirImpl = getDefaultImpl("Rmdir");
		symlinkImpl = getDefaultImpl("Symlink");
		renameImpl = getDefaultImpl("Rename");
		linkImpl = getDefaultImpl("Link");
		openImpl = getDefaultImpl("Open");
		readImpl = getDefaultImpl("Read");
		writeImpl = getDefaultImpl("Write");
		flushImpl = getDefaultImpl("Flush");
		releaseImpl = getDefaultImpl("Release");
		fsyncImpl = getDefaultImpl("Fsync");
		opendirImpl = getDefaultImpl("Opendir");
		readdirImpl = getDefaultImpl("Readdir");
		releasedirImpl = getDefaultImpl("Releasedir");
		fsyncdirImpl = getDefaultImpl("Fsyncdir");
		statfsImpl = getDefaultImpl("Statfs");
		setxattrImpl = getDefaultImpl("Setxattr");
		getxattrImpl = getDefaultImpl("Getxattr");
		listxattrImpl = getDefaultImpl("Listxattr");
		removexattrImpl = getDefaultImpl("Removexattr");
		accessImpl = getDefaultImpl("Access");
		createImpl = getDefaultImpl("Create");
		bmapImpl = getDefaultImpl("Bmap");
	}

	public static <T extends JLowFuseTask<? extends Context>> Class<T> getDefaultImpl(String name) {
		return getImpl(TASK_PACKAGE + "." + name);
	}

}
