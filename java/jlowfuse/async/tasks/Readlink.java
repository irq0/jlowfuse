package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public class Readlink extends FilesystemOperation {
	protected long ino;

	public Readlink(FuseReq req, long ino) {
		super(req);
		this.ino = ino;
	}
}
