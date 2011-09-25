package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.async.tasks.JLowFuseTask;

public class Mknod extends JLowFuseTask implements jlowfuse.async.tasks.Mknod {
	public void attributes(FuseReq req, long parent, String name, short mode, short rdev) {
	}

    public void run() {
	    super.run();
    }	
}
