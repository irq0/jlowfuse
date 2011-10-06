package jlowfuse.async.tasks;

import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Lookup<CTX extends Context> extends FilesystemOperation<CTX> {
	protected long parent;
	protected String name;
	
	public Lookup(FuseReq req, long parent, String name) {
		super(req);
		this.parent = parent;
		this.name = name;
	}
}
