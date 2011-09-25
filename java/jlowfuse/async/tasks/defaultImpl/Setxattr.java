package jlowfuse.async.tasks.defaultImpl;

import java.nio.ByteBuffer;

import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Setxattr extends JLowFuseTask implements jlowfuse.async.tasks.Setxattr {
	public void setSetxattrAttributes(FuseReq req, long ino, String name, ByteBuffer value, int flags) {
	}

    public void run() {
	    super.run();
    }	
}
