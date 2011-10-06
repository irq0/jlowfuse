package objectfs.async.tasks;

import fuse.Errno;
import objectfs.Inode;
import objectfs.async.ObjectFsContext;
import jlowfuse.FuseReq;
import jlowfuse.Reply;

public class Unlink extends jlowfuse.async.tasks.Unlink<ObjectFsContext> {

	public Unlink(FuseReq req, long parent, String name) {
		super(req, parent, name);
	}
	
	public void run() {
        Inode parentInode = context.fs.getInodeByIno(parent);
        Inode childInode = context.fs.getChildInodeByName(parentInode, name);

        if (childInode != null) {
            context.fs.removeInode(childInode);            
            Reply.err(req, 0);
        } else {
            Reply.err(req, Errno.ENOENT);
        }
	}

}
