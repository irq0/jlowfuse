package jlowfuse.async.tasks;

import jlowfuse.FuseReq;
import jlowfuse.Reply;
import jlowfuse.async.Context;
import fuse.Errno;

public class FilesystemOperation<CTX extends Context> extends JLowFuseTask<CTX> {
	protected FuseReq req;
	
	public FilesystemOperation(FuseReq req) {
		this.req = req;
	}
	public void run() {
        Reply.err(req, Errno.ENOSYS);
	}
	public String toString() {
		return new StringBuilder(super.toString())
			.append(" [")
			.append(req.toString())
			.append("]")
			.toString();
	}
}
