package jlowfuse.async.tasks.defaultImpl;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.Reply;
import jlowfuse.async.tasks.JLowFuseTask;

public class Opendir extends JLowFuseTask {
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
