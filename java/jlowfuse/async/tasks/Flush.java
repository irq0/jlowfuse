package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Flush<CTX extends Context> extends FilesystemOperation<CTX> {
	protected long ino;
	protected FileInfo fi;
	public Flush(FuseReq req, long ino, FileInfo fi) {
		super(req);
		this.ino = ino;
		this.fi = fi;
	}
}
