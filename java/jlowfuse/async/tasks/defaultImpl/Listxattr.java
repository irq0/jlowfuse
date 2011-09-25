package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Listxattr extends JLowFuseTask {
	protected long ino;
	protected int size;
	
	public Listxattr(FuseReq req, long ino, int size) {
		super(req);
		this.ino = ino;
		this.size = size;
	}
}