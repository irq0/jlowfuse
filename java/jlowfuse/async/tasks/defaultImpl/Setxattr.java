package jlowfuse.async.tasks.defaultImpl;

import java.nio.ByteBuffer;

import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Setxattr extends JLowFuseTask {
	protected long ino;
	protected String name;
	protected ByteBuffer value;
	protected int flags;
	
	public Setxattr(FuseReq req, long ino, String name, ByteBuffer value, int flags) {
		super(req);
		this.ino = ino;
		this.name = name;
		this.value = value;
		this.flags = flags;
	}
}