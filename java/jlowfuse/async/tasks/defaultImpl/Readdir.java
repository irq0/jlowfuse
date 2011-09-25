package jlowfuse.async.tasks.defaultImpl;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Readdir extends JLowFuseTask implements jlowfuse.async.tasks.Readdir {
	public void attributes(FuseReq req, long ino, long size, long off, FileInfo fi) {
	}

    public void run() {
	    super.run();
    }	
}
