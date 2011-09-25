package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.async.tasks.JLowFuseTask;

public class Fsyncdir extends JLowFuseTask implements jlowfuse.async.tasks.Fsyncdir {
	public void attributes(FuseReq req, long ino, int datasync, FileInfo fi) {
	}

    public void run() {
	    super.run();
    }	
}
