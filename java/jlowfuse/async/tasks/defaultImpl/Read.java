package jlowfuse.async.tasks.defaultImpl;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Read extends JLowFuseTask implements jlowfuse.async.tasks.Read {
	public void setReadAttributes(FuseReq req, long ino, long size, long off, FileInfo fi) {
	}

    public void run() {
	    super.run();
    }	
}
