package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.Reply;

public class Releasedir extends FilesystemOperation {
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