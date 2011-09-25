package jlowfuse.async.tasks;

import fuse.StatVFS;
import jlowfuse.FuseReq;
import jlowfuse.Reply;

public class Statfs extends FilesystemOperation {
	protected long ino;
	
	public Statfs(FuseReq req, long ino) {
		super(req);
		this.ino = ino;
	}

    public void run() {
        StatVFS stat = new StatVFS();
        stat.setNamemax(255);
        stat.setBsize(512);

        Reply.statfs(req, stat);
    }	
}
