package jlowfuse.async.tasks.defaultImpl;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Getattr extends JLowFuseTask implements jlowfuse.async.tasks.Getattr {
	public void setGetattrAttributes(FuseReq req, long ino, FileInfo fi) {
	}

    public void run() {
	    super.run();
    }	
}
