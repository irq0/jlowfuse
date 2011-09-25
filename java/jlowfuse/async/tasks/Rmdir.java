package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public class Rmdir extends FilesystemOperation {
	protected long parent;
	protected String name;
	
	public Rmdir(FuseReq req, long parent, String name) {
		super(req);
		this.parent = parent;
		this.name = name;
	}
}
