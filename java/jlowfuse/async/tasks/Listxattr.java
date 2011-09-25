package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public class Listxattr extends FilesystemOperation {
	protected long ino;
	protected int size;
	
	public Listxattr(FuseReq req, long ino, int size) {
		super(req);
		this.ino = ino;
		this.size = size;
	}
}
