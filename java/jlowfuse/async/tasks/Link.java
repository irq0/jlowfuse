package jlowfuse.async.tasks;

import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Link <CTX extends Context> extends FilesystemOperation<CTX> {
	protected long ino;
	protected long newparent;
	protected String newname;
	
	public Link(FuseReq req, long ino, long newparent, String newname) {
		super(req);
		this.ino = ino;
		this.newparent = newparent;
		this.newname = newname;
	}
	
	public String toString() {
		return new StringBuilder(super.toString())
			.append(" ino=")
			.append(ino)
			.append(" newparent=")
			.append(newparent)
			.append(" newname=")
			.append(newname)	
			.toString();
	}}
