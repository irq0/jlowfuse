package jlowfuse.async.tasks;

import jlowfuse.FuseReq;
import jlowfuse.Reply;
import fuse.Errno;

public class FilesystemOperation extends BaseOperation {
	protected FuseReq req;
	
	public FilesystemOperation(FuseReq req) {
		this.req = req;
	}
	public void run() {
        Reply.err(req, Errno.ENOSYS);
	}
}
