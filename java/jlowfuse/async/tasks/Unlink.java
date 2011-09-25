package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public class Unlink extends FilesystemOperation {
	protected long parent;
	protected String name;
	
	public Unlink(FuseReq req, long parent, String name) {
		super(req);
		this.parent = parent;
		this.name = name;
	}
}
