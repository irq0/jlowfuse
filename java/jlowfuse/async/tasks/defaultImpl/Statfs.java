package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Statfs extends JLowFuseTask implements jlowfuse.async.tasks.Statfs {
	public void attributes(FuseReq req, long ino) {
	}

    public void run() {
	    super.run();
    }	
}
