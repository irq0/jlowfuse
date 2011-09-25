package jlowfuse.async.tasks.defaultImpl;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Flush extends JLowFuseTask implements jlowfuse.async.tasks.Flush {
	public void attributes(FuseReq req, long ino, FileInfo fi) {
	}

    public void run() {
	    super.run();
    }	
}
