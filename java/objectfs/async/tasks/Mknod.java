package objectfs.async.tasks;

import fuse.Errno;
import fuse.Stat;
import fuse.StatConstants;
import objectfs.Inode;
import objectfs.async.ObjectFsContext;
import jlowfuse.FuseReq;
import jlowfuse.Reply;

public class Mknod extends jlowfuse.async.tasks.Mknod<ObjectFsContext> {

	public Mknod(FuseReq req, long parent, String name, short mode, short rdev) {
		super(req, parent, name, mode, rdev);
	}
	public void run() {
        Inode parentInode = context.fs.getInodeByIno(parent);

        if (parentInode != null) {            
            Inode inode = context.fs.createAndWireNewInode(parentInode, name);
            Stat s = new Stat();
            s.setIno(inode.getIno());
            s.setNlink(1);
            s.setMode((short)(StatConstants.IFREG | mode));
            s.setUid(0);
            s.setGid(0);
            inode.setStat(s);
        
            Reply.entry(req, inode.getEntryParam());
        } else {
            Reply.err(req, Errno.EPERM);
        }
	}
}
