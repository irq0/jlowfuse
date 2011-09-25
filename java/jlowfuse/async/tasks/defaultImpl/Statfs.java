package jlowfuse.async.tasks.defaultImpl;

import fuse.StatVFS;
import jlowfuse.FuseReq;
import jlowfuse.Reply;
import jlowfuse.async.tasks.JLowFuseTask;

public class Statfs extends JLowFuseTask {
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
