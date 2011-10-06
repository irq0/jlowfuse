/**
 * Proxy class for FUSE Operations
 * The c ops functions call this methods which then perform type wrapping and
 * call the methods in the `LowlevelOps` class.
 *
 * @author Marcel Lauhoff <ml@irq0.org>
 * 
 */

package jlowfuse;

import java.nio.ByteBuffer;

import fuse.FileInfo;
import fuse.Stat;

public class LowlevelOpsProxy implements OpsProxy {
	private LowlevelOps ops = null;

	public void register(LowlevelOps ops) {
		this.ops = ops;
	}

	@Override
	public void init(long data, long conn) {
		if (this.ops == null)
			throw new RuntimeException("LowlevelOps unregistered");
		this.ops.init();
	}

	@Override
	public void destroy(long data) {
		ops.destroy();
	}

	@Override
	public void lookup(long req, long parent, String name) {
		ops.lookup(new FuseReq(req), parent, name);
	}

	@Override
	public void forget(long req, long ino, long nlookup) {
		ops.forget(new FuseReq(req), ino, nlookup);
	}

	@Override
	public void getattr(long req, long ino, long fi) {
		ops.getattr(new FuseReq(req), ino, new FileInfo(fi, false));
	}

	@Override
	public void setattr(long req, long ino, long attr, int to_set, long fi) {
		ops.setattr(new FuseReq(req), ino, new Stat(attr, false), to_set, new FileInfo(fi, false));
	}

	@Override
	public void readlink(long req, long ino) {
		ops.readlink(new FuseReq(req), ino);
	}

	@Override
	public void mknod(long req, long parent, String name, short mode, short rdev) {
		ops.mknod(new FuseReq(req), parent, name, mode, rdev);
	}

	@Override
	public void mkdir(long req, long parent, String name, short mode) {
		ops.mkdir(new FuseReq(req), parent, name, mode);
	}

	@Override
	public void unlink(long req, long parent, String name) {
		ops.unlink(new FuseReq(req), parent, name);
	}

	@Override
	public void rmdir(long req, long parent, String name) {
		ops.rmdir(new FuseReq(req), parent, name);
	}

	@Override
	public void symlink(long req, String link, long parent, String name) {
		ops.symlink(new FuseReq(req), link, parent, name);
	}

	@Override
	public void rename(long req, long parent, String name, long newparent, String newname) {
		ops.rename(new FuseReq(req), parent, name, newparent, newname);
	}

	@Override
	public void link(long req, long ino, long newparent, String newname) {
		ops.link(new FuseReq(req), ino, newparent, newname);
	}

	@Override
	public void open(long req, long ino, long fi) {
		ops.open(new FuseReq(req), ino, new FileInfo(fi, false));
	}

	@Override
	public void read(long req, long ino, long size, long off, long fi) {
		ops.read(new FuseReq(req), ino, size, off, new FileInfo(fi, false));
	}

	@Override
	public void write(long req, long ino, ByteBuffer buf, long size, long off, long fi) {
		ops.write(new FuseReq(req), ino, buf, off, new FileInfo(fi, false));
	}

	@Override
	public void flush(long req, long ino, long fi) {
		ops.flush(new FuseReq(req), ino, new FileInfo(fi, false));
	}

	@Override
	public void release(long req, long ino, long fi) {
		ops.release(new FuseReq(req), ino, new FileInfo(fi, false));
	}

	@Override
	public void fsync(long req, long ino, int datasync, long fi) {
		ops.fsync(new FuseReq(req), ino, datasync, new FileInfo(fi, false));
	}

	@Override
	public void opendir(long req, long ino, long fi) {
		ops.opendir(new FuseReq(req), ino, new FileInfo(fi, false));
	}

	@Override
	public void readdir(long req, long ino, long size, long off, long fi) {
		ops.readdir(new FuseReq(req), ino, size, off, new FileInfo(fi, false));
	}

	@Override
	public void releasedir(long req, long ino, long fi) {
		ops.releasedir(new FuseReq(req), ino, new FileInfo(fi, false));
	}

	@Override
	public void fsyncdir(long req, long ino, int datasync, long fi) {
		ops.fsyncdir(new FuseReq(req), ino, datasync, new FileInfo(fi, false));
	}

	@Override
	public void statfs(long req, long ino) {
		ops.statfs(new FuseReq(req), ino);
	}

	@Override
	public void setxattr(long req, long ino, String name, ByteBuffer value, int size, int flags) {
		ops.setxattr(new FuseReq(req), ino, name, value, flags);
	}

	@Override
	public void getxattr(long req, long ino, String name, int size) {
		ops.getxattr(new FuseReq(req), ino, name, size);
	}

	@Override
	public void listxattr(long req, long ino, int size) {
		ops.listxattr(new FuseReq(req), ino, size);
	}

	@Override
	public void removexattr(long req, long ino, String name) {
		ops.removexattr(new FuseReq(req), ino, name);
	}

	@Override
	public void access(long req, long ino, int mask) {
		ops.access(new FuseReq(req), ino, mask);
	}

	@Override
	public void create(long req, long parent, String name, short mode, long fi) {
		ops.create(new FuseReq(req), parent, name, mode, new FileInfo(fi, false));
	}

	/*
	 * public void getlk(long ino, Flock lock) { }
	 * 
	 * public void setlk(long ino, Flock lock, int sleep) { }
	 */

	public void bmap(long req, long ino, int blocksize, long idx) {
		ops.bmap(new FuseReq(req), ino, blocksize, idx);
	}

	/*
	 * public void ioctl(long ino, int cmd, ByteBuffer arg,
	 */

	/*
	 * public void poll(long ino, PollHandle ph) { }
	 */
}
