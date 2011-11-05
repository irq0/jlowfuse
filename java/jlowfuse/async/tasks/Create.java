package jlowfuse.async.tasks;

import jlowfuse.FuseReq;
import jlowfuse.async.Context;
import fuse.FileInfo;

public class Create<CTX extends Context> extends FilesystemOperation<CTX> {
	protected long parent;
	protected String name;
	protected short mode;
	protected FileInfo fi;
	
	public Create(FuseReq req, long parent, String name, short mode, FileInfo fi) {
		super(req);
		this.parent = parent;
		this.name = name;
		this.mode = mode;
		this.fi = fi;		
	}
	
	public String toString() {
		return new StringBuilder(super.toString())
			.append(" parent=")
			.append(parent)
			.append(" name=")
			.append(name)
			.append(" mode=")
			.append(mode)	
			.toString();
	}
}
