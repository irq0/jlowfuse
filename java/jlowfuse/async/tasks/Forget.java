package jlowfuse.async.tasks;

import jlowfuse.FuseReq;
import jlowfuse.Reply;

public class Forget extends FilesystemOperation {
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
