package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public class Lookup extends FilesystemOperation {
	protected long parent;
	protected String name;
	
	public Lookup(FuseReq req, long parent, String name) {
		super(req);
		this.parent = parent;
		this.name = name;
	}
}
