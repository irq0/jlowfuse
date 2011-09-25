package jlowfuse.async.tasks.defaultImpl;

import fuse.FileInfo;
import fuse.Stat;
import jlowfuse.FuseReq;
import jlowfuse.async.tasks.JLowFuseTask;

public class Setattr extends JLowFuseTask {
	protected long ino;
	protected Stat attr;
	protected int to_set;
	protected FileInfo fi;
	
	public Setattr(FuseReq req, long ino, Stat attr, int to_set, FileInfo fi) {
		super(req);
		this.ino = ino;
		this.attr = attr;
		this.to_set = to_set;
		this.fi = fi;
	}
}