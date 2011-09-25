package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.Reply;

public class Release extends FilesystemOperation {
	protected long ino;
	protected FileInfo fi;
	
	public Release(FuseReq req, long ino, FileInfo fi) {
		super(req);
		this.ino = ino;
		this.fi = fi;
    }
	
	public void run() {
	    Reply.err(req, 0);
    }
}
