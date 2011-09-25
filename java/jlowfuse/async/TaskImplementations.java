package jlowfuse.async;

import jlowfuse.async.tasks.*;

public interface TaskImplementations {
	public Class<Init> initImpl = null;
	public Class<Destroy> destroyImpl = null;
	public Class<Lookup> lookupImpl = null;
	public Class<Forget> forgetImpl = null;
	public Class<Getattr> getattrImpl = null;
	public Class<Setattr> setattrImpl = null;
	public Class<Readlink> readlinkImpl = null;
	public Class<Mknod> mknodImpl = null;
	public Class<Mkdir> mkdirImpl = null;
	public Class<Unlink> unlinkImpl = null;
	public Class<Rmdir> rmdirImpl = null;
	public Class<Symlink> symlinkImpl = null;
	public Class<Rename> renameImpl = null;
	public Class<Link> linkImpl = null;
	public Class<Open> openImpl = null;
	public Class<Read> readImpl = null;
	public Class<Write> writeImpl = null;
	public Class<Flush> flushImpl = null;
	public Class<Release> releaseImpl = null;
	public Class<Fsync> fsyncImpl = null;
	public Class<Opendir> opendirImpl = null;
	public Class<Readdir> readdirImpl = null;
	public Class<Releasedir> releasedirImpl = null;
	public Class<Fsyncdir> fsyncdirImpl = null;
	public Class<Statfs> statfsImpl = null;
	public Class<Setxattr> setxattrImpl = null;
	public Class<Getxattr> getxattrImpl = null;
	public Class<Listxattr> listxattrImpl = null;
	public Class<Removexattr> removexattrImpl = null;
	public Class<Access> accessImpl = null;
	public Class<Create> createImpl = null;
	public Class<Bmap> bmapImpl = null;
}