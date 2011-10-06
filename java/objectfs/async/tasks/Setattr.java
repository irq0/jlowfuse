package objectfs.async.tasks;

import objectfs.Inode;
import objectfs.ObjectFS;
import objectfs.async.ObjectFsContext;
import jlowfuse.FuseReq;
import jlowfuse.Reply;
import fuse.Errno;
import fuse.FileInfo;
import fuse.FuseConstants;
import fuse.Stat;

public class Setattr extends jlowfuse.async.tasks.Setattr<ObjectFsContext> {

	public Setattr(FuseReq req, long ino, Stat attr, int to_set, FileInfo fi) {
	    super(req, ino, attr, to_set, fi);
    }

	public void run() {
		ObjectFS fs = context.fs;
        Inode inode = fs.getInodeByIno(ino);

        if (inode == null) {
            Reply.err(req, Errno.ENOENT);
        }

        Stat s = inode.getStat();

        switch(to_set) {
        case FuseConstants.FUSE_SET_ATTR_MODE:
            s.setMode(attr.getMode());
            break;
        case FuseConstants.FUSE_SET_ATTR_UID:
            s.setUid(attr.getUid());
            break;
        case FuseConstants.FUSE_SET_ATTR_GID:
            s.setGid(attr.getGid());
            break;
        case FuseConstants.FUSE_SET_ATTR_SIZE:
            s.setSize(attr.getSize());
            break;
        case FuseConstants.FUSE_SET_ATTR_ATIME:
        case FuseConstants.FUSE_SET_ATTR_ATIME_NOW:
        case FuseConstants.FUSE_SET_ATTR_MTIME:
        case FuseConstants.FUSE_SET_ATTR_MTIME_NOW:
        default:
            Reply.err(req, Errno.ENOSYS);
        }        
        Reply.attr(req, inode.getStat(), 0.0);
	}

}
