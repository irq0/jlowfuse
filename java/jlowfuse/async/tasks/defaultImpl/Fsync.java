package jlowfuse.async.tasks.defaultImpl;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Fsync extends JLowFuseTask implements jlowfuse.async.tasks.Fsync {
	public void attributes(FuseReq req, long ino, int datasync, FileInfo fi) {
	}

    public void run() {
	    super.run();
    }	
}
