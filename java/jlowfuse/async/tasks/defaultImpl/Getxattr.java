package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Getxattr extends JLowFuseTask implements jlowfuse.async.tasks.Getxattr {
	public void attributes(FuseReq req, long ino, String name, int size) {
	}

    public void run() {
	    super.run();
    }	
}
