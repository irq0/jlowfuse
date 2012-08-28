/*
 * Copyright (c) 2011 Marcel Lauhoff.
 * 
 * This file is part of jlowfuse.
 * 
 * jlowfuse is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * jlowfuse is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with jlowfuse.  If not, see <http://www.gnu.org/licenses/>.
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

	
	public void init(long data, long conn) {
		if (this.ops == null)
			throw new RuntimeException("LowlevelOps unregistered");
		this.ops.init();
	}

	
	public void destroy(long data) {
		ops.destroy();
	}

	
	public void lookup(long req, long parent, String name) {
		ops.lookup(new FuseReq(req), parent, name);
	}

	
	public void forget(long req, long ino, long nlookup) {
		ops.forget(new FuseReq(req), ino, nlookup);
	}

	
	public void getattr(long req, long ino, long fi) {
		ops.getattr(new FuseReq(req), ino, new FileInfo(fi, false));
	}

	
	public void setattr(long req, long ino, long attr, int to_set, long fi) {
		ops.setattr(new FuseReq(req), ino, new Stat(attr, false), to_set, new FileInfo(fi, false));
	}

	
	public void readlink(long req, long ino) {
		ops.readlink(new FuseReq(req), ino);
	}

	
	public void mknod(long req, long parent, String name, short mode, short rdev) {
		ops.mknod(new FuseReq(req), parent, name, mode, rdev);
	}

	
	public void mkdir(long req, long parent, String name, short mode) {
		ops.mkdir(new FuseReq(req), parent, name, mode);
	}

	
	public void unlink(long req, long parent, String name) {
		ops.unlink(new FuseReq(req), parent, name);
	}

	
	public void rmdir(long req, long parent, String name) {
		ops.rmdir(new FuseReq(req), parent, name);
	}

	
	public void symlink(long req, String link, long parent, String name) {
		ops.symlink(new FuseReq(req), link, parent, name);
	}

	
	public void rename(long req, long parent, String name, long newparent, String newname) {
		ops.rename(new FuseReq(req), parent, name, newparent, newname);
	}

	
	public void link(long req, long ino, long newparent, String newname) {
		ops.link(new FuseReq(req), ino, newparent, newname);
	}

	
	public void open(long req, long ino, long fi) {
		ops.open(new FuseReq(req), ino, new FileInfo(fi, false));
	}

	
	public void read(long req, long ino, long size, long off, long fi) {
		ops.read(new FuseReq(req), ino, size, off, new FileInfo(fi, false));
	}

	
	public void write(long req, long ino, ByteBuffer buf, long size, long off, long fi) {
		buf.limit((int) size); // XXX remove cast in java 7
		ops.write(new FuseReq(req), ino, buf, off, new FileInfo(fi, false));
	}

	
	public void flush(long req, long ino, long fi) {
		ops.flush(new FuseReq(req), ino, new FileInfo(fi, false));
	}

	
	public void release(long req, long ino, long fi) {
		ops.release(new FuseReq(req), ino, new FileInfo(fi, false));
	}

	
	public void fsync(long req, long ino, int datasync, long fi) {
		ops.fsync(new FuseReq(req), ino, datasync, new FileInfo(fi, false));
	}

	
	public void opendir(long req, long ino, long fi) {
		ops.opendir(new FuseReq(req), ino, new FileInfo(fi, false));
	}

	
	public void readdir(long req, long ino, long size, long off, long fi) {
		ops.readdir(new FuseReq(req), ino, size, off, new FileInfo(fi, false));
	}

	
	public void releasedir(long req, long ino, long fi) {
		ops.releasedir(new FuseReq(req), ino, new FileInfo(fi, false));
	}

	
	public void fsyncdir(long req, long ino, int datasync, long fi) {
		ops.fsyncdir(new FuseReq(req), ino, datasync, new FileInfo(fi, false));
	}

	
	public void statfs(long req, long ino) {
		ops.statfs(new FuseReq(req), ino);
	}

	
	public void setxattr(long req, long ino, String name, ByteBuffer value, int size, int flags) {
		ops.setxattr(new FuseReq(req), ino, name, value, flags);
	}

	
	public void getxattr(long req, long ino, String name, int size) {
		ops.getxattr(new FuseReq(req), ino, name, size);
	}

	
	public void listxattr(long req, long ino, int size) {
		ops.listxattr(new FuseReq(req), ino, size);
	}

	
	public void removexattr(long req, long ino, String name) {
		ops.removexattr(new FuseReq(req), ino, name);
	}

	
	public void access(long req, long ino, int mask) {
		ops.access(new FuseReq(req), ino, mask);
	}

	
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
