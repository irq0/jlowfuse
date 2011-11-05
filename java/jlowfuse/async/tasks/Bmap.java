package jlowfuse.async.tasks;

import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Bmap<CTX extends Context> extends FilesystemOperation<CTX> {
	protected long ino;
	protected int blocksize;
	protected long idx;
	
	public Bmap(FuseReq req, long ino, int blocksize, long idx) {
		super(req);
		this.ino = ino;
		this.blocksize = blocksize;
		this.idx = idx;
	}
	
	public String toString() {
		return new StringBuilder(super.toString())
			.append(" ino=")
			.append(ino)
			.append(" blocksize=")
			.append(blocksize)
			.append(" idx=")
			.append(idx)	
			.toString();
	}
}
