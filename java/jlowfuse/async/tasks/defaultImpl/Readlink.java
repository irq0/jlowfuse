package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Readlink extends JLowFuseTask {
	protected long ino;

	public Readlink(FuseReq req, long ino) {
		super(req);
		this.ino = ino;
	}
}