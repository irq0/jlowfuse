package jlowfuse.async.tasks;

import java.nio.ByteBuffer;

import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Setxattr <CTX extends Context> extends FilesystemOperation<CTX> {
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
	
	public String toString() {
		return new StringBuilder(super.toString())
			.append(" ino=")
			.append(ino)
			.toString();
	}
}
