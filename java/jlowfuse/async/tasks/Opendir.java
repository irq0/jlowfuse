package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.Reply;
import jlowfuse.async.Context;

public class Opendir<CTX extends Context> extends FilesystemOperation<CTX> {
	protected long ino;
	protected FileInfo fi;
	
	public Opendir(FuseReq req, long ino, FileInfo fi) {
		super(req);
		this.ino = ino;
		this.fi = fi;
	}

    public void run() {
    	Reply.open(req, fi);
    }	
}
