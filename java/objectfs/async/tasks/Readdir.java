package objectfs.async.tasks;

import fuse.Dirbuf;
import fuse.Errno;
import fuse.FileInfo;
import fuse.FuseExtra;
import jlowfuse.FuseReq;
import jlowfuse.Reply;
import objectfs.Inode;
import objectfs.async.ObjectFsContext;

public class Readdir extends jlowfuse.async.tasks.Readdir<ObjectFsContext> {

	public Readdir(FuseReq req, long ino, long size, long off, FileInfo fi) {
		super(req, ino, size, off, fi);
	}

	public void run() {
        Inode inode = context.fs.getInodeByIno(ino);
        Dirbuf d = new Dirbuf();

        FuseExtra.dirbufAdd(req, d, ".", inode.getIno(),
                            inode.getStat().getMode());
        FuseExtra.dirbufAdd(req, d, "..", inode.getParent().getIno(),
                            inode.getParent().getStat().getMode());

        for (Inode c: inode.getChildren()) {
            FuseExtra.dirbufAdd(req, d,
                                c.getName(),
                                c.getIno(),
                                c.getStat().getMode());
        }
                
        if (inode != null) {
	        Reply.dirBufLimited(req, d, off, size);
        } else {
            Reply.err(req, Errno.ENOTDIR);
        }
	}
}
