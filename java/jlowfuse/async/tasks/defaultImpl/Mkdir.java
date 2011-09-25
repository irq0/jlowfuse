package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Mkdir extends JLowFuseTask implements jlowfuse.async.tasks.Mkdir {
	public void setMkdirAttributes(FuseReq req, long parent, String name, short mode) {
	}

    public void run() {
	    super.run();
    }	
}
