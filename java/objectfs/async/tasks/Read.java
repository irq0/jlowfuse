package objectfs.async.tasks;

import java.nio.ByteBuffer;

import objectfs.Inode;
import objectfs.async.ObjectFsContext;
import jlowfuse.FuseReq;
import jlowfuse.Reply;
import fuse.FileInfo;

public class Read extends jlowfuse.async.tasks.Read<ObjectFsContext> {

	public Read(FuseReq req, long ino, long size, long off, FileInfo fi) {
		super(req, ino, size, off, fi);
	}
	
	public void run() {
        Inode inode = context.fs.getInodeByIno(ino);
        ByteBuffer buf = inode.getData();

        System.out.println("READ  " + buf + " off=" + off + " size=" + size);
        if (buf != null) {        
            Reply.byteBuffer(req, buf, off, size);
        } else {
	        buf = ByteBuffer.allocateDirect((int)size);            
            Reply.byteBuffer(req, buf, off, size);
        }	
	}

}
