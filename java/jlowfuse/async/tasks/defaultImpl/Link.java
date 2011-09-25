package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Link extends JLowFuseTask {
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