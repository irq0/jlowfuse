package jlowfuse.async.tasks.defaultImpl;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Release extends JLowFuseTask implements jlowfuse.async.tasks.Release {
	public void setReleaseAttributes(FuseReq req, long ino, FileInfo fi) {
	    
    }
	
	public void run() {
	    super.run();
    }
}
