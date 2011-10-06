package objectfs.async.tasks;

import fuse.Errno;
import fuse.Stat;
import fuse.StatConstants;
import objectfs.Inode;
import objectfs.async.ObjectFsContext;
import jlowfuse.FuseReq;
import jlowfuse.Reply;

public class Mkdir extends jlowfuse.async.tasks.Mkdir<ObjectFsContext> {

	public Mkdir(FuseReq req, long parent, String name, short mode) {
		super(req, parent, name, mode);
	}

	public void run() {
        Inode parentInode = context.fs.getInodeByIno(parent);

        if (parentInode != null) {            
            Inode inode = context.fs.createAndWireNewInode(parentInode, name);
            Stat s = new Stat();
            s.setIno(inode.getIno());
            s.setNlink(2);
            s.setMode((short)(StatConstants.IFDIR | mode));
            s.setUid(0);
            s.setGid(0);
            inode.setStat(s);
        
            Reply.entry(req, inode.getEntryParam());
        } else {
            Reply.err(req, Errno.EPERM);
        }
	}
}
