package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Getattr<CTX extends Context> extends FilesystemOperation<CTX> {
	protected long ino;
	protected FileInfo fi;

	public Getattr(FuseReq req, long ino, FileInfo fi) {
		super(req);
		this.ino = ino;
		this.fi = fi;
	}
}
