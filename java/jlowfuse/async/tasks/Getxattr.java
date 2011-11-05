package jlowfuse.async.tasks;

import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Getxattr<CTX extends Context> extends FilesystemOperation<CTX> {
	protected long ino;
	protected String name;
	protected int size;
	
	public Getxattr(FuseReq req, long ino, String name, int size) {
		super(req);
		this.ino = ino;
		this.name = name;
		this.size = size;
	}
	
	public String toString() {
		return new StringBuilder(super.toString())
			.append(" ino=")
			.append(ino)
			.append(" size=")
			.append(size)
			.append(" name=")
			.append(name)	
			.toString();
	}}
