package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;

public class Fsyncdir extends FilesystemOperation {
	protected long ino;
	protected int datasync;
	protected FileInfo fi;
	
	public Fsyncdir(FuseReq req, long ino, int datasync, FileInfo fi) {
		super(req);
		this.ino = ino;
		this.datasync = datasync;
		this.fi = fi;
	}
}
