package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;

public class Flush extends FilesystemOperation {
	protected long ino;
	protected FileInfo fi;
	public Flush(FuseReq req, long ino, FileInfo fi) {
		super(req);
		this.ino = ino;
		this.fi = fi;
	}
}
