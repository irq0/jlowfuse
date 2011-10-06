package objectfs.async.tasks;

import jlowfuse.FuseReq;
import jlowfuse.Reply;
import fuse.Errno;
import fuse.FileInfo;
import objectfs.Inode;
import objectfs.async.ObjectFsContext;


public class Getattr extends jlowfuse.async.tasks.Getattr<ObjectFsContext> {

	public Getattr(FuseReq req, long ino, FileInfo fi) {
		super(req, ino, fi);
	}

	public void run() {
        Inode inode = context.fs.getInodeByIno(ino);
        
        if (inode != null) {
        	Reply.attr(req, inode.getStat(), 0.0);
        } else {
        	Reply.err(req, Errno.ENOENT);
        }
	}
}
