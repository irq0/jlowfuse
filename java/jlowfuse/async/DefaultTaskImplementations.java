/**
 * Data structure class to hold the mapping to 
 * the implemantations of jlowfuse async tasks
 */

package jlowfuse.async;

import jlowfuse.async.tasks.*;

public class DefaultTaskImplementations implements TaskImplementations{
	public Class<Init> initImpl = Init.class;
	public Class<Destroy> destroyImpl = Destroy.class;
	public Class<Lookup> lookupImpl = Lookup.class;
	public Class<Forget> forgetImpl = Forget.class;
	public Class<Getattr> getattrImpl = Getattr.class;
	public Class<Setattr> setattrImpl = Setattr.class;
	public Class<Readlink> readlinkImpl = Readlink.class;
	public Class<Mknod> mknodImpl = Mknod.class;
	public Class<Mkdir> mkdirImpl = Mkdir.class;
	public Class<Unlink> unlinkImpl = Unlink.class;
	public Class<Rmdir> rmdirImpl = Rmdir.class;
	public Class<Symlink> symlinkImpl = Symlink.class;
	public Class<Rename> renameImpl = Rename.class;
	public Class<Link> linkImpl = Link.class;
	public Class<Open> openImpl = Open.class;
	public Class<Read> readImpl = Read.class;
	public Class<Write> writeImpl = Write.class;
	public Class<Flush> flushImpl = Flush.class;
	public Class<Release> releaseImpl = Release.class;
	public Class<Fsync> fsyncImpl = Fsync.class;
	public Class<Opendir> opendirImpl = Opendir.class;
	public Class<Readdir> readdirImpl = Readdir.class;
	public Class<Releasedir> releasedirImpl = Releasedir.class;
	public Class<Fsyncdir> fsyncdirImpl = Fsyncdir.class;
	public Class<Statfs> statfsImpl = Statfs.class;
	public Class<Setxattr> setxattrImpl = Setxattr.class;
	public Class<Getxattr> getxattrImpl = Getxattr.class;
	public Class<Listxattr> listxattrImpl = Listxattr.class;
	public Class<Removexattr> removexattrImpl = Removexattr.class;
	public Class<Access> accessImpl = Access.class;
	public Class<Create> createImpl = Create.class;
	public Class<Bmap> bmapImpl = Bmap.class;

	private Class taskIntfByName(String name) {
		try {
			Class result = Class.forName("jlowfuse.async.tasks." + name);
			return result;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e); /* should not happen */
		}
	}	
}