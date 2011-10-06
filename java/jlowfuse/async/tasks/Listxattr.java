package jlowfuse.async.tasks;

import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Listxattr<CTX extends Context> extends FilesystemOperation<CTX> {
	protected long ino;
	protected int size;
	
	public Listxattr(FuseReq req, long ino, int size) {
		super(req);
		this.ino = ino;
		this.size = size;
	}
}
