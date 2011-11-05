package jlowfuse.async.tasks;

import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Symlink<CTX extends Context> extends FilesystemOperation<CTX> {
	protected String link;
	protected long parent;
	protected String name;
	
	public Symlink(FuseReq req, String link, long parent, String name) {
		super(req);
		this.link = link;
		this.parent = parent;
		this.name = name;
	}
	
	public String toString() {
		return new StringBuilder(super.toString())
			.append(" link=")
			.append(link)
			.append(" parent=")
			.append(parent)
			.append(" name=")
			.append(name)	
			.toString();
	}
}
