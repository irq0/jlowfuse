package objectfs;

import fuse.*;
import jlowfuse.*;
import java.math.BigInteger;
import java.util.Hashtable;

class ObjectFsOps extends AbstractLowlevelOps {
    Inode root;
    Hashtable<Long, Inode> inode_table = new Hashtable<Long, Inode>();
    
    public void init() {
        root = new Inode(1, null);
        inode_table.put(1L, root);
    }

    public void readdir(FuseReq req, long ino, int size, int off, fuse_file_info fi) {
        Inode inode = inode_table.get(ino);
        Directory dir = new Directory();

        System.out.println(inode);
        dir.add(req, 1, ".");
        
        if (inode != null) {
            fuse.fuse_reply_buf(req, dir, size);

        } else {
            fuse.fuse_reply_err(req, fuseConstants.ENOTDIR);
        }
    }
    
    public void statfs(FuseReq req, long ino) {
        statvfs stat = new statvfs();
        
        stat.setBsize(1024);
        stat.setFrsize(1024);
        stat.setBfree(new BigInteger("19"));
        stat.setBlocks(new BigInteger("42"));
        stat.setFiles(new BigInteger("23"));
        stat.setFavail(new BigInteger("5"));
        
        fuse.fuse_reply_statfs(req, stat);
    }


    public void getattr(FuseReq req, long ino, fuse_file_info fi) {
        Inode inode = inode_table.get(ino);

        
	if (inode != null) {
            stat stat = new stat();
            
            stat.setIno(ino);
            stat.setNlink(3);
            stat.setMode(fuseConstants.S_IFDIR | 0755);
            
            fuse.fuse_reply_attr(req, stat, 0.0);
	} else {
		fuse.fuse_reply_err(req, fuseConstants.ENOENT);
	}

    }   
}
