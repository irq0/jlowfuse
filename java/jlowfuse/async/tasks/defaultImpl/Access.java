package jlowfuse.async.tasks.defaultImpl;

import fuse.Errno;
import jlowfuse.FuseReq;
import jlowfuse.Reply;
import jlowfuse.async.tasks.JLowFuseTask;

public class Access extends JLowFuseTask {
	protected long ino;
	protected long mask;
	
	public Access(FuseReq req, long ino, int mask) {
		super(req);
		this.ino = ino;
		this.mask = mask;
	}

    public void run() {
	    super.run();
        Reply.err(req, Errno.ENOSYS);
    }	
}
