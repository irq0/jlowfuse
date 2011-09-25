package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public class Symlink extends FilesystemOperation {
	protected String link;
	protected long parent;
	protected String name;
	
	public Symlink(FuseReq req, String link, long parent, String name) {
		super(req);
		this.link = link;
		this.parent = parent;
		this.name = name;
	}
}
