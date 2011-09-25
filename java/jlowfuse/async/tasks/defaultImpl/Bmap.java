package jlowfuse.async.tasks.defaultImpl;

import fuse.Errno;
import jlowfuse.FuseReq;
import jlowfuse.Reply;
import jlowfuse.async.tasks.JLowFuseTask;

public class Bmap extends JLowFuseTask {
	protected long ino;
	protected int blocksize;
	protected long idx;
	
	public Bmap(FuseReq req, long ino, int blocksize, long idx) {
		super(req);
		this.ino = ino;
		this.blocksize = blocksize;
		this.idx = idx;
	}
}