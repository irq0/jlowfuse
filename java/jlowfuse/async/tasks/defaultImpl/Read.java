package jlowfuse.async.tasks.defaultImpl;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Read extends JLowFuseTask {
	public Read(FuseReq req, long ino, long size, long off, FileInfo fi) {
	}

    public void run() {
	    super.run();
    }	
}
