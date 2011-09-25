package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public class Mknod extends FilesystemOperation {
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
