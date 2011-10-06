/**
 * Data structure class to hold the mapping to 
 * the implemantations of jlowfuse async tasks
 */

package jlowfuse.async;

import jlowfuse.async.tasks.*;

public class DefaultTaskImplementations {
	public Class<? extends Init> initImpl = Init.class; 
	public Class<? extends Destroy> destroyImpl = Destroy.class;
	public Class<? extends Lookup> lookupImpl = Lookup.class;
	public Class<? extends Forget> forgetImpl = Forget.class;
	public Class<? extends Getattr> getattrImpl = Getattr.class;
	public Class<? extends Setattr> setattrImpl = Setattr.class;
	public Class<? extends Readlink> readlinkImpl = Readlink.class;
	public Class<? extends Mknod> mknodImpl = Mknod.class;
	public Class<? extends Mkdir> mkdirImpl = Mkdir.class;
	public Class<? extends Unlink> unlinkImpl = Unlink.class;
	public Class<? extends Rmdir> rmdirImpl = Rmdir.class;
	public Class<? extends Symlink> symlinkImpl = Symlink.class;
	public Class<? extends Rename> renameImpl = Rename.class;
	public Class<? extends Link> linkImpl = Link.class;
	public Class<? extends Open> openImpl = Open.class;
	public Class<? extends Read> readImpl = Read.class;
	public Class<? extends Write> writeImpl = Write.class;
	public Class<? extends Flush> flushImpl = Flush.class;
	public Class<? extends Release> releaseImpl = Release.class;
	public Class<? extends Fsync> fsyncImpl = Fsync.class;
	public Class<? extends Opendir> opendirImpl = Opendir.class;
	public Class<? extends Readdir> readdirImpl = Readdir.class;
	public Class<? extends Releasedir> releasedirImpl = Releasedir.class;
	public Class<? extends Fsyncdir> fsyncdirImpl = Fsyncdir.class;
	public Class<? extends Statfs> statfsImpl = Statfs.class;
	public Class<? extends Setxattr> setxattrImpl = Setxattr.class;
	public Class<? extends Getxattr> getxattrImpl = Getxattr.class;
	public Class<? extends Listxattr> listxattrImpl = Listxattr.class;
	public Class<? extends Removexattr> removexattrImpl = Removexattr.class;
	public Class<? extends Access> accessImpl = Access.class;
	public Class<? extends Create> createImpl = Create.class;
	public Class<? extends Bmap> bmapImpl = Bmap.class;
}
