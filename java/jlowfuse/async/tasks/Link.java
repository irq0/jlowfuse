package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public class Link extends FilesystemOperation {
	protected long ino;
	protected long newparent;
	protected String newname;
	
	public Link(FuseReq req, long ino, long newparent, String newname) {
		super(req);
		this.ino = ino;
		this.newparent = newparent;
		this.newname = newname;
	}
}
