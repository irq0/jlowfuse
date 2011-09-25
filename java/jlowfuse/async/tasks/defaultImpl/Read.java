package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.async.tasks.JLowFuseTask;

public class Read extends JLowFuseTask implements jlowfuse.async.tasks.Read {
	public void attributes(FuseReq req, long ino, long size, long off, FileInfo fi) {
	}

    public void run() {
	    super.run();
    }	
}
