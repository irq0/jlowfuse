package jlowfuse.async.tasks.defaultImpl;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Fsyncdir extends JLowFuseTask {
	public Fsyncdir(FuseReq req, long ino, int datasync, FileInfo fi) {
	}

    public void run() {
	    super.run();
    }	
}
