package objectfs;

import fuse.*;
import jlowfuse.*;
import java.math.BigInteger;
import java.util.Hashtable;

class ObjectFsOps extends AbstractLowlevelOps {
    Inode root;
    Hashtable<Long, Inode> inode_table = new Hashtable<Long, Inode>();
    public void init() {
        root = new Inode(null, "root");
        root.setParent(root);
        inode_table.put(1L, root);

        stat s = new stat();
        s.setIno(root.getIno());
        s.setNlink(3L);
        s.setMode(fuseConstants.S_IFDIR | 0777);
        s.setUid(0);
        s.setGid(0);
        root.setStat(s);
        
        Inode child = new Inode(root, "test");
        wireInode(child);

        s = new stat();
        s.setIno(child.getIno());
        s.setNlink(2L);
        s.setMode(fuseConstants.S_IFREG | 0777);
        s.setUid(0);
        s.setGid(0);
        child.setStat(s);        
    }

    private void wireInode(Inode inode) {
        inode_table.put(inode.getIno(), inode);
        inode.getParent().addChild(inode);
    }

    private Inode getChildInodeByName(Inode parent, String name) {
        for (Inode i: parent.getChildren()) {
            if (name.equals(i.getName())) {
                return i;
            }
        }
        return null;
    }

    private Inode getInodeByIno(long ino) {
        return inode_table.get(ino);
    }


    private void removeInode(Inode inode) {
        Inode parent = inode.getParent();
        
        parent.getChildren().remove(inode);
        inode.setParent(null);
        inode_table.remove(inode);
    }
        
        
    
    private void mkdirnod(FuseReq req, long parentIno, String name, short fullMode) {
        Inode parent = inode_table.get(parentIno);

        if (parent != null) {            
            Inode inode = new Inode(parent, name);
            wireInode(inode);

            stat s = new stat();
            s.setIno(inode.getIno());
            s.setNlink(1L);
            s.setMode(fullMode);
            s.setUid(0);
            s.setGid(0);
            inode.setStat(s);
            
            fuse_entry_param e = new fuse_entry_param();
            e.setGeneration(23);
            e.setIno(inode.getIno());
            e.setAttr_timeout(0.0);
            e.setEntry_timeout(0.0);
            e.setAttr(s);

            fuse.fuse_reply_entry(req, e);
        } else {
            fuse.fuse_reply_err(req, fuseConstants.EPERM);
        }
    }
        

    public void mkdir(FuseReq req, long parent, String name, short mode) {
        mkdirnod(req, parent, name, (short)(fuseConstants.S_IFDIR | mode));
    }
        
    public void mknod(FuseReq req, long parent, String name, short mode,
                      short rdev) {
        mkdirnod(req, parent, name, (short)(fuseConstants.S_IFREG | mode));
    }

    public void rmdir(FuseReq req, long parentIno, String name) {
        Inode parent = getInodeByIno(parentIno);
        Inode child = getChildInodeByName(parent, name);

        if (child != null) {
            removeInode(child);            
            fuse.fuse_reply_err(req, 0);
        } else {
            fuse.fuse_reply_err(req, fuseConstants.ENOENT);
        }
    }

    public void unlink(FuseReq req, long parent, String name) {
        rmdir(req, parent, name);        
    }    
    
    public void readdir(FuseReq req, long ino, int size, int off, fuse_file_info fi) {
        Inode inode = inode_table.get(ino);
        dirbuf d = new dirbuf();

        fuse_extra.fuse_extra_dirbuf_add(req, d, ".", inode.getIno(),
                                         fuseConstants.S_IFDIR | 0777);
        fuse_extra.fuse_extra_dirbuf_add(req, d, "..", inode.getParent().getIno(),
                                         fuseConstants.S_IFDIR | 0777);

        for (Inode c: inode.getChildren()) {
            fuse_extra.fuse_extra_dirbuf_add(req, d,
                                             c.getName(),
                                             c.getIno(),
                                             fuseConstants.S_IFDIR | 0777);
        }
                
        if (inode != null) {
            fuse_extra.fuse_extra_reply_buf_limited(req, d.getP(), d.getSize(),
                                                    off, size);
        } else {
            fuse.fuse_reply_err(req, fuseConstants.ENOTDIR);
        }
    }

    public void lookup(FuseReq req, long ino, String name) {
        Inode parent = inode_table.get(ino);

        if (parent != null) {
            Inode child = getChildInodeByName(parent, name);
            
            if (child != null) {
                fuse_entry_param e = new fuse_entry_param();
                e.setGeneration(23);
                e.setIno(child.getIno());
                e.setAttr_timeout(0.0);
                e.setEntry_timeout(0.0);
                e.setAttr(child.getStat());

                fuse.fuse_reply_entry(req, e);
            } else {
                fuse.fuse_reply_err(req, fuseConstants.ENOENT);
            }
        }
    }
            
    public void statfs(FuseReq req, long ino) {
        statvfs s = new statvfs();
        
        s.setBsize(1024);
	s.setFrsize(1024);
	s.setBfree(new BigInteger("19"));
	s.setBlocks(new BigInteger("42"));
	s.setFiles(new BigInteger("23"));
	s.setFavail(new BigInteger("5"));
        
        fuse.fuse_reply_statfs(req, s);
    }

    public void getattr(FuseReq req, long ino, fuse_file_info fi) {
        Inode inode = inode_table.get(ino);
        
	if (inode != null) {
            fuse.fuse_reply_attr(req, inode.getStat(), 0.0);
	} else {
            fuse.fuse_reply_err(req, fuseConstants.ENOENT);
	}

    }   
}




