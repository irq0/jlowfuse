package jlowfuse.async.tasks;

import jlowfuse.FuseReq;
import fuse.FileInfo;

public class Create extends FilesystemOperation {
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
}
