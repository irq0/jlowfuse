package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Access extends JLowFuseTask implements jlowfuse.async.tasks.Access {
	public void setAccessAttributes(FuseReq req, long ino, int mask) {
	}

    public void run() {
	    super.run();
    }	
}
