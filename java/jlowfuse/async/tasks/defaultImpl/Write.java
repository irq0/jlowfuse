package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.async.tasks.JLowFuseTask;

public class Write extends JLowFuseTask implements jlowfuse.async.tasks.Write {
	public void attributes(FuseReq req, long ino, ByteBuffer buf, long off, FileInfo fi) {
	}

    public void run() {
	    super.run();
    }	
}
