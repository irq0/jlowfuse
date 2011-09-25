package jlowfuse.async.tasks.defaultImpl;

import java.nio.ByteBuffer;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Write extends JLowFuseTask {
	public Write(FuseReq req, long ino, ByteBuffer buf, long off, FileInfo fi) {
	}

    public void run() {
	    super.run();
    }	
}
