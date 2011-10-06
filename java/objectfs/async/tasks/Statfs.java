package objectfs.async.tasks;

import fuse.StatVFS;
import objectfs.async.ObjectFsContext;
import jlowfuse.FuseReq;
import jlowfuse.Reply;

public class Statfs extends jlowfuse.async.tasks.Statfs<ObjectFsContext> {

	public Statfs(FuseReq req, long ino) {
		super(req, ino);
	}
	
	public void run() {
        StatVFS s = new StatVFS();
        
        s.setBsize(1);
        s.setFrsize(1024);
        s.setBfree(19);
        s.setBlocks(42);
        s.setFiles(23);
        s.setFavail(5);
        
        Reply.statfs(req, s);
	}

}
