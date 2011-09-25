package jlowfuse.async.tasks.defaultImpl;

import jlowfuse.FuseReq;
import jlowfuse.Reply;
import jlowfuse.async.tasks.JLowFuseTask;

public class Forget extends JLowFuseTask {
	protected long ino;
	protected long nlookup;
	
	public Forget(FuseReq req, long ino, long nlookup) {
		super(req);
		this.ino = ino;
		this.nlookup = nlookup;
	}

    public void run() {
	    Reply.none(req);
    }	
}