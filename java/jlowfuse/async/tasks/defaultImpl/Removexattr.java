package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Removexattr extends JLowFuseTask {
	protected long ino;
	protected String name;
	
	public Removexattr(FuseReq req, long ino, String name) {
		super(req);
		this.ino = ino;
		this.name = name;
	}
}
