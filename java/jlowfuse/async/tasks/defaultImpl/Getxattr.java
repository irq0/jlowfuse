package jlowfuse.async.tasks.defaultImpl;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Getxattr extends JLowFuseTask {
	protected long ino;
	protected String name;
	protected int size;
	
	public Getxattr(FuseReq req, long ino, String name, int size) {
		super(req);
		this.ino = ino;
		this.name = name;
		this.size = size;
	}
}