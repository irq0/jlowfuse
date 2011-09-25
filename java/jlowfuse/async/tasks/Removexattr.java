package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public class Removexattr extends FilesystemOperation {
	protected long ino;
	protected String name;
	
	public Removexattr(FuseReq req, long ino, String name) {
		super(req);
		this.ino = ino;
		this.name = name;
	}
}
