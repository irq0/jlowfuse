package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Symlink extends JLowFuseTask {
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