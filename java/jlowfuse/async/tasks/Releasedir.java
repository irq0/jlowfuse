package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.Reply;
import jlowfuse.async.Context;

public class Releasedir<CTX extends Context> extends FilesystemOperation<CTX> {
	protected long ino;
	protected FileInfo fi;
	
	public Releasedir(FuseReq req, long ino, FileInfo fi) {
		super(req);
		this.ino = ino;
		this.fi = fi;
    }
	
	public void run() {
	    Reply.err(req, 0);
    }
}
