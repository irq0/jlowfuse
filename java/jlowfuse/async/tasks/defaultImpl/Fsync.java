package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.async.tasks.JLowFuseTask;

public class Fsync extends JLowFuseTask implements jlowfuse.async.tasks.Fsync {
	public void attributes(FuseReq req, long ino, int datasync, FileInfo fi) {
	}

    public void run() {
	    super.run();
    }	
}
