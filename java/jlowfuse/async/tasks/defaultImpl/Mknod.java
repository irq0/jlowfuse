package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Mknod extends JLowFuseTask {
	protected long parent;
	protected String name;
	protected short mode;
	protected short rdev;
	
	public Mknod(FuseReq req, long parent, String name, short mode, short rdev) {
		super(req);
		this.parent = parent;
		this.name = name;
		this.mode = mode;
		this.rdev = rdev;
	}
}