package jlowfuse.async.tasks;

import jlowfuse.FuseReq;
import jlowfuse.Reply;
import jlowfuse.async.Context;

public class Forget<CTX extends Context> extends FilesystemOperation<CTX> {
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
