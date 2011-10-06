package jlowfuse.async;

import jlowfuse.async.tasks.*;

public interface TaskImplementations {
	public Class<? extends Init<? extends Context>> initImpl = null;
	public Class<? extends Destroy<? extends Context>> destroyImpl = null;
	public Class<? extends Lookup<? extends Context>> lookupImpl = null;
	public Class<? extends Forget<? extends Context>> forgetImpl = null;
	public Class<? extends Getattr<? extends Context>> getattrImpl = null;
	public Class<? extends Setattr<? extends Context>> setattrImpl = null;
	public Class<? extends Readlink<? extends Context>> readlinkImpl = null;
	public Class<? extends Mknod<? extends Context>> mknodImpl = null;
	public Class<? extends Mkdir<? extends Context>> mkdirImpl = null;
	public Class<? extends Unlink<? extends Context>> unlinkImpl = null;
	public Class<? extends Rmdir<? extends Context>> rmdirImpl = null;
	public Class<? extends Symlink<? extends Context>> symlinkImpl = null;
	public Class<? extends Rename<? extends Context>> renameImpl = null;
	public Class<? extends Link<? extends Context>> linkImpl = null;
	public Class<? extends Open<? extends Context>> openImpl = null;
	public Class<? extends Read<? extends Context>> readImpl = null;
	public Class<? extends Write<? extends Context>> writeImpl = null;
	public Class<? extends Flush<? extends Context>> flushImpl = null;
	public Class<? extends Release<? extends Context>> releaseImpl = null;
	public Class<? extends Fsync<? extends Context>> fsyncImpl = null;
	public Class<? extends Opendir<? extends Context>> opendirImpl = null;
	public Class<? extends Readdir<? extends Context>> readdirImpl = null;
	public Class<? extends Releasedir<? extends Context>> releasedirImpl = null;
	public Class<? extends Fsyncdir<? extends Context>> fsyncdirImpl = null;
	public Class<? extends Statfs<? extends Context>> statfsImpl = null;
	public Class<? extends Setxattr<? extends Context>> setxattrImpl = null;
	public Class<? extends Getxattr<? extends Context>> getxattrImpl = null;
	public Class<? extends Listxattr<? extends Context>> listxattrImpl = null;
	public Class<? extends Removexattr<? extends Context>> removexattrImpl = null;
	public Class<? extends Access<? extends Context>> accessImpl = null;
	public Class<? extends Create<? extends Context>> createImpl = null;
	public Class<? extends Bmap<? extends Context>> bmapImpl = null;
}
