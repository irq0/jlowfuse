package jlowfuse.async.tasks;

import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Rename <CTX extends Context> extends FilesystemOperation<CTX> {
	protected long parent;
	protected String name;
	protected long newparent;
	protected String newname;
	
	public Rename(FuseReq req, long parent, String name, long newparent, String newname) {
		super(req);
		this.parent = parent;
		this.name = name;
		this.newparent = newparent;
		this.newname = newname;
	}
	
	public String toString() {
		return new StringBuilder(super.toString())
			.append(" parent=")
			.append(parent)
			.append(" name=")
			.append(name)
			.append(" newparent=")
			.append(newparent)	
			.append(" newname=")
			.append(newname)
			.toString();
	}
}
