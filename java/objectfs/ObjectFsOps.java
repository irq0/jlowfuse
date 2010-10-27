package objectfs;

import fuse.*;
import jlowfuse.*;
import java.math.BigInteger;
import java.nio.*;
import java.util.*;

class ObjectFsOps extends AbstractLowlevelOps {
    Inode root;
    Hashtable<Long, Inode> inode_table = new Hashtable<Long, Inode>();
    public void init() {
        root = new Inode(null, "");
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

        Inode p = inode.getParent();
        p.addChild(inode);
        p.getStat().setNlink(p.getStat().getNlink() + 1);
    }

    private Inode getChildInodeByName(Inode parent, String name) {
        for (Inode i: parent.getChildren()) {
            if (name.equals(i.getName())) {
                return i;
            }
        }
        return null;
    }

    private void updateSize(Inode inode) {    
        inode.getStat().setSize(BigInteger.valueOf(inode.getData().capacity()));
    }
    
    private Inode getInodeByIno(long ino) {
        return inode_table.get(ino);
    }

    private void removeInode(Inode inode) {
        Inode parent = inode.getParent();
        
        parent.getChildren().remove(inode);
        parent.getStat().setNlink(parent.getStat().getNlink() - 1);
        inode.setParent(null);
        inode_table.remove(inode);
    }
        
    private void mkdirnod(FuseReq req, long parentIno, String name,
                          short fullMode, long nlink) {
        Inode parent = getInodeByIno(parentIno);

        if (parent != null) {            
            Inode inode = new Inode(parent, name);
            wireInode(inode);

            stat s = new stat();
            s.setIno(inode.getIno());
            s.setNlink(nlink);
            s.setMode(fullMode);
            s.setUid(0);
            s.setGid(0);
            //            s.setSize();
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


    public void read(FuseReq req, long ino, int size, int off, fuse_file_info fi) {
        Inode inode = getInodeByIno(ino);
        ByteBuffer buf = inode.getData();

        System.out.println("READ  " + buf + " off=" + off + " size=" + size);
        if (buf != null) {        
            Reply.replyByteBuffer(req, buf, off, size);
        } else {
            buf = ByteBuffer.allocateDirect(size);            
            Reply.replyByteBuffer(req, buf, off, size);
        }
    }


    public void write(FuseReq req, long ino, ByteBuffer src, int off,
                      fuse_file_info fi) {
        Inode inode = getInodeByIno(ino);
        ByteBuffer dst = inode.getData();

        if (dst == null) { // uninitialized
            ByteBuffer buf = ByteBuffer.allocateDirect(Math.max(src.capacity() + off,
                                                                4096));
            buf.position(off);
            buf.put(src);

            dst = buf;
            inode.setData(buf);
        } else if (dst.capacity() < (src.capacity() + off)) { // to small
            System.out.println(dst  + "    "  + src);
            ByteBuffer buf = ByteBuffer.allocateDirect(
                                  Math.max(dst.capacity() + src.capacity() + off,
                                           dst.capacity() + 1024*1024));
            buf.put(dst);
            buf.position(off);
            buf.put(src);

            dst = buf;
            inode.setData(buf);
        } else {                        
            dst.position(off);
            dst.put(src);
        }

        dst.rewind();
        updateSize(inode);
        fuse.fuse_reply_write(req, src.capacity());
    }

    public void mkdir(FuseReq req, long parent, String name, short mode) {
        mkdirnod(req, parent, name, (short)(fuseConstants.S_IFDIR | mode), 2);
    }
        
    public void mknod(FuseReq req, long parent, String name, short mode,
                      short rdev) {
        mkdirnod(req, parent, name, (short)(fuseConstants.S_IFREG | mode), 1);
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
        Inode inode = getInodeByIno(ino);
        dirbuf d = new dirbuf();

        fuse_extra.fuse_extra_dirbuf_add(req, d, ".", inode.getIno(),
                                         inode.getStat().getMode());
        fuse_extra.fuse_extra_dirbuf_add(req, d, "..", inode.getParent().getIno(),
                                         inode.getParent().getStat().getMode());

        for (Inode c: inode.getChildren()) {
            fuse_extra.fuse_extra_dirbuf_add(req, d,
                                             c.getName(),
                                             c.getIno(),
                                             c.getStat().getMode());
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
            System.out.println(child);
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
        
        s.setBsize(1);
	s.setFrsize(1024);
	s.setBfree(new BigInteger("19"));
	s.setBlocks(new BigInteger("42"));
	s.setFiles(new BigInteger("23"));
	s.setFavail(new BigInteger("5"));
        
        fuse.fuse_reply_statfs(req, s);
    }

    public void getattr(FuseReq req, long ino, fuse_file_info fi) {
        Inode inode = getInodeByIno(ino);
        
	if (inode != null) {
            fuse.fuse_reply_attr(req, inode.getStat(), 0.0);
	} else {
            fuse.fuse_reply_err(req, fuseConstants.ENOENT);
	}

    }
    
    public void setattr(FuseReq req, long ino, stat stat, int to_set,
                        fuse_file_info fi) {
        Inode inode = getInodeByIno(ino);

        if (inode == null) {
            fuse.fuse_reply_err(req, fuseConstants.ENOENT);
        }

        stat s = inode.getStat();

        switch(to_set) {
        case fuseConstants.FUSE_SET_ATTR_MODE:
            s.setMode(stat.getMode());
            break;
        case fuseConstants.FUSE_SET_ATTR_UID:
            s.setUid(stat.getUid());
            break;
        case fuseConstants.FUSE_SET_ATTR_GID:
            s.setGid(stat.getGid());
            break;
        case fuseConstants.FUSE_SET_ATTR_SIZE:
            s.setSize(stat.getSize());
            break;
        case fuseConstants.FUSE_SET_ATTR_ATIME:
        case fuseConstants.FUSE_SET_ATTR_ATIME_NOW:
        case fuseConstants.FUSE_SET_ATTR_MTIME:
        case fuseConstants.FUSE_SET_ATTR_MTIME_NOW:
        default:
            fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
        }        
        fuse.fuse_reply_attr(req, inode.getStat(), 0.0);
    }

    public void access(FuseReq req, long ino, int mask) {
        fuse.fuse_reply_err(req, 0);
    }

}
