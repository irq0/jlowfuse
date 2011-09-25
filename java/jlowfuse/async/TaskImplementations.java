package jlowfuse.async;

import jlowfuse.async.tasks.*;

public interface TaskImplementations {
	public Class<?> initImpl = null;
	public Class<?> destroyImpl = null;
	public Class<?> lookupImpl = null;
	public Class<?> forgetImpl = null;
	public Class<?> getattrImpl = null;
	public Class<?> setattrImpl = null;
	public Class<?> readlinkImpl = null;
	public Class<?> mknodImpl = null;
	public Class<?> mkdirImpl = null;
	public Class<?> unlinkImpl = null;
	public Class<?> rmdirImpl = null;
	public Class<?> symlinkImpl = null;
	public Class<?> renameImpl = null;
	public Class<?> linkImpl = null;
	public Class<?> openImpl = null;
	public Class<?> readImpl = null;
	public Class<?> writeImpl = null;
	public Class<?> flushImpl = null;
	public Class<?> releaseImpl = null;
	public Class<?> fsyncImpl = null;
	public Class<?> opendirImpl = null;
	public Class<?> readdirImpl = null;
	public Class<?> releasedirImpl = null;
	public Class<?> fsyncdirImpl = null;
	public Class<?> statfsImpl = null;
	public Class<?> setxattrImpl = null;
	public Class<?> getxattrImpl = null;
	public Class<?> listxattrImpl = null;
	public Class<?> removexattrImpl = null;
	public Class<?> accessImpl = null;
	public Class<?> createImpl = null;
	public Class<?> bmapImpl = null;
}