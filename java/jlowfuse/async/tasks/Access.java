package jlowfuse.async.tasks;

import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Access<CTX extends Context> extends FilesystemOperation<CTX> {
	protected long ino;
	protected long mask;
	
	public Access(FuseReq req, long ino, int mask) {
		super(req);
		this.ino = ino;
		this.mask = mask;
	}
}
