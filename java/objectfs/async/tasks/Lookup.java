package objectfs.async.tasks;

import fuse.Errno;
import jlowfuse.FuseReq;
import jlowfuse.Reply;
import objectfs.Inode;
import objectfs.async.ObjectFsContext;

public class Lookup extends jlowfuse.async.tasks.Lookup<ObjectFsContext> {

	public Lookup(FuseReq req, long parent, String name) {
		super(req, parent, name);
	}

	public void run() {
        Inode parentInode = context.fs.getInodeByIno(parent);

        if (parentInode != null) {
            Inode child = context.fs.getChildInodeByName(parentInode, name);
            System.out.println(child);
            if (child != null) {
                Reply.entry(req, child.getEntryParam());
            } else {
                Reply.err(req, Errno.ENOENT);
            }
        }		
	}
}
