package jlowfuse.async.tasks.defaultImpl;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Releasedir extends JLowFuseTask implements jlowfuse.async.tasks.Releasedir {
	public void setReleasedirAttributes(FuseReq req, long ino, FileInfo fi) {
	}

    public void run() {
	    super.run();
    }	
}
