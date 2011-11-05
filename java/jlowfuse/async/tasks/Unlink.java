package jlowfuse.async.tasks;

import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Unlink <CTX extends Context> extends FilesystemOperation<CTX> {
	protected long parent;
	protected String name;
	
	public Unlink(FuseReq req, long parent, String name) {
		super(req);
		this.parent = parent;
		this.name = name;
	}
	
	public String toString() {
		return new StringBuilder(super.toString())
			.append(" parent=")
			.append(parent)
			.append(" name=")
			.append(name)	
			.toString();
	}
}
