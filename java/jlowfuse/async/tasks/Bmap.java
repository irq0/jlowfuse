package jlowfuse.async.tasks;

import fuse.Errno;
import jlowfuse.FuseReq;
import jlowfuse.Reply;

public class Bmap extends FilesystemOperation {
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
