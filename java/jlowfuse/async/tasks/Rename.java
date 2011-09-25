package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public class Rename extends FilesystemOperation {
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
}
