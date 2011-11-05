package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Fsyncdir <CTX extends Context> extends FilesystemOperation<CTX> {
	protected long ino;
	protected int datasync;
	protected FileInfo fi;
	
	public Fsyncdir(FuseReq req, long ino, int datasync, FileInfo fi) {
		super(req);
		this.ino = ino;
		this.datasync = datasync;
		this.fi = fi;
	}
	
	public String toString() {
		return new StringBuilder(super.toString())
			.append(" ino=")
			.append(ino)	
			.toString();
	}
}
