package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;

public class Readdir extends FilesystemOperation {
	protected long ino;
	protected long size;;
	protected long off;
	protected FileInfo fi;
	
	public Readdir(FuseReq req, long ino, long size, long off, FileInfo fi) {
		super(req);
		this.ino = ino;
		this.size = size;
		this.off = off;
		this.fi = fi;
	}
}
