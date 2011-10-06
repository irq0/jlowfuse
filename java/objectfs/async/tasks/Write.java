package objectfs.async.tasks;
import java.nio.ByteBuffer;

import jlowfuse.FuseReq;
import jlowfuse.Reply;
import fuse.FileInfo;
import objectfs.Inode;
import objectfs.async.ObjectFsContext;

public class Write extends jlowfuse.async.tasks.Write<ObjectFsContext> {

	public Write(FuseReq req, long ino, ByteBuffer buf, long off, FileInfo fi) {
		super(req, ino, buf, off, fi);
	}
	
	public void run() {
        Inode inode = context.fs.getInodeByIno(ino);
        long written = inode.writeData(buf, off);
        Reply.write(req, written);
	}

}
