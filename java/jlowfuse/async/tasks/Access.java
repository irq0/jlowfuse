package jlowfuse.async.tasks;

import fuse.Errno;
import jlowfuse.FuseReq;
import jlowfuse.Reply;

public class Access extends FilesystemOperation {
	protected long ino;
	protected long mask;
	
	public Access(FuseReq req, long ino, int mask) {
		super(req);
		this.ino = ino;
		this.mask = mask;
	}
}
