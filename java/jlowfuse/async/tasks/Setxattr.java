package jlowfuse.async.tasks;

import java.nio.ByteBuffer;

import jlowfuse.FuseReq;

public class Setxattr extends FilesystemOperation {
	protected long ino;
	protected String name;
	protected ByteBuffer value;
	protected int flags;
	
	public Setxattr(FuseReq req, long ino, String name, ByteBuffer value, int flags) {
		super(req);
		this.ino = ino;
		this.name = name;
		this.value = value;
		this.flags = flags;
	}
}
