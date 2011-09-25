package jlowfuse.async.tasks.defaultImpl;

import fuse.FileInfo;
import fuse.Stat;
import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Setattr extends JLowFuseTask implements jlowfuse.async.tasks.Setattr {
	public void attributes(FuseReq req, long ino, Stat attr, int to_set, FileInfo fi) {
	}

    public void run() {
	    super.run();
    }	
}
