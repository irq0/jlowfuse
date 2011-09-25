package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Mkdir extends JLowFuseTask {
	protected long parent;
	protected String name;
	protected short mode;
	
	public Mkdir(FuseReq req, long parent, String name, short mode) {
		super(req);
		this.parent = parent;
		this.name = name;
		this.mode = mode;
	}
}