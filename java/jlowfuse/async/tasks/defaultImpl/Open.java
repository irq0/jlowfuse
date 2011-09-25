package jlowfuse.async.tasks.defaultImpl;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Open extends JLowFuseTask implements jlowfuse.async.tasks.Open {
	public void setOpenAttributes(FuseReq req, long ino, FileInfo fi) {
	}

    public void run() {
	    super.run();
    }	
}
