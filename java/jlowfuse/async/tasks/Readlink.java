package jlowfuse.async.tasks;

import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Readlink<CTX extends Context> extends FilesystemOperation<CTX> {
	protected long ino;

	public Readlink(FuseReq req, long ino) {
		super(req);
		this.ino = ino;
	}
	
	public String toString() {
		return new StringBuilder(super.toString())
			.append(" ino=")
			.append(ino)
			.toString();
	}
}
