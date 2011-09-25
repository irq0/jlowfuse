package jlowfuse.async.tasks.defaultImpl;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Create extends JLowFuseTask implements jlowfuse.async.tasks.Create {
	public void attributes(FuseReq req, long parent, String name, short mode, FileInfo fi) {
	}

    public void run() {
	    super.run();
    }	
}
