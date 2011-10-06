package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Fsync<CTX extends Context> extends FilesystemOperation<CTX> {
	protected long ino;
	protected int datasync;
	protected FileInfo fi;
	
	public Fsync(FuseReq req, long ino, int datasync, FileInfo fi) {
		super(req);
		this.ino = ino;
		this.datasync = datasync;
		this.fi = fi;
	}
}
