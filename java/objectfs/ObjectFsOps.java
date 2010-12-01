package objectfs;

import fuse.*;
import jlowfuse.*;
import java.util.Hashtable;
import java.nio.ByteBuffer;

class ObjectFsOps extends AbstractLowlevelOps {
    Inode root;
    Hashtable<Long, Inode> inode_table = new Hashtable<Long, Inode>();
    public void init() {
        root = new Inode(null, "");
        root.setParent(root);
        inode_table.put(1L, root);

        Stat s = new Stat();
        s.setIno(root.getIno());
        s.setNlink(3L);
        s.setMode(StatConstants.IFDIR | 0777);
        s.setUid(0);
        s.setGid(0);
        root.setStat(s);
        
        Inode child = new Inode(root, "test");
        wireInode(child);

        s = new Stat();
        s.setIno(child.getIno());
        s.setNlink(2L);
        s.setMode(StatConstants.IFREG | 0777);
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
        inode.getStat().setSize(inode.getData().capacity());
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

            Stat s = new Stat();
            s.setIno(inode.getIno());
            s.setNlink(nlink);
            s.setMode(fullMode);
            s.setUid(0);
            s.setGid(0);
            //            s.setSize();
            inode.setStat(s);
            
            EntryParam e = new EntryParam();
            e.setGeneration(23);
            e.setIno(inode.getIno());
            e.setAttr_timeout(0.0);
            e.setEntry_timeout(0.0);
            e.setAttr(s);

            Reply.entry(req, e);
        } else {
            Reply.err(req, Errno.EPERM);
        }
    }


    public void read(FuseReq req, long ino, int size, int off, FileInfo fi) {
        Inode inode = getInodeByIno(ino);
        ByteBuffer buf = inode.getData();

        System.out.println("READ  " + buf + " off=" + off + " size=" + size);
        if (buf != null) {        
            Reply.byteBuffer(req, buf, off, size);
        } else {
            buf = ByteBuffer.allocateDirect(size);            
            Reply.byteBuffer(req, buf, off, size);
        }
    }


    public void write(FuseReq req, long ino, ByteBuffer src, int off,
                      FileInfo fi) {
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
        Reply.write(req, src.capacity());
    }

    public void mkdir(FuseReq req, long parent, String name, short mode) {
        mkdirnod(req, parent, name, (short)(StatConstants.IFDIR | mode), 2);
    }
        
    public void mknod(FuseReq req, long parent, String name, short mode,
                      short rdev) {
        mkdirnod(req, parent, name, (short)(StatConstants.IFREG | mode), 1);
    }

    public void rmdir(FuseReq req, long parentIno, String name) {
        Inode parent = getInodeByIno(parentIno);
        Inode child = getChildInodeByName(parent, name);

        if (child != null) {
            removeInode(child);            
            Reply.err(req, 0);
        } else {
            Reply.err(req, Errno.ENOENT);
        }
    }

    public void unlink(FuseReq req, long parent, String name) {
        rmdir(req, parent, name);        
    }    
    
    public void readdir(FuseReq req, long ino, int size, int off, FileInfo fi) {
        Inode inode = getInodeByIno(ino);
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

    public void lookup(FuseReq req, long ino, String name) {
        Inode parent = inode_table.get(ino);

        if (parent != null) {
            Inode child = getChildInodeByName(parent, name);
            System.out.println(child);
            if (child != null) {
                EntryParam e = new EntryParam();
                e.setGeneration(23);
                e.setIno(child.getIno());
                e.setAttr_timeout(0.0);
                e.setEntry_timeout(0.0);
                e.setAttr(child.getStat());

                Reply.entry(req, e);
            } else {
                Reply.err(req, Errno.ENOENT);
            }
        }
    }
            
    public void statfs(FuseReq req, long ino) {
        StatVFS s = new StatVFS();
        
        s.setBsize(1);
        s.setFrsize(1024);
        s.setBfree(19);
        s.setBlocks(42);
        s.setFiles(23);
        s.setFavail(5);
        
        Reply.statfs(req, s);
    }

    public void getattr(FuseReq req, long ino, FileInfo fi) {
        Inode inode = getInodeByIno(ino);
        
	if (inode != null) {
            Reply.attr(req, inode.getStat(), 0.0);
	} else {
            Reply.err(req, Errno.ENOENT);
	}

    }
    
    public void setattr(FuseReq req, long ino, Stat stat, int to_set,
                        FileInfo fi) {
        Inode inode = getInodeByIno(ino);

        if (inode == null) {
            Reply.err(req, Errno.ENOENT);
        }

        Stat s = inode.getStat();

        switch(to_set) {
        case FuseConstants.FUSE_SET_ATTR_MODE:
            s.setMode(stat.getMode());
            break;
        case FuseConstants.FUSE_SET_ATTR_UID:
            s.setUid(stat.getUid());
            break;
        case FuseConstants.FUSE_SET_ATTR_GID:
            s.setGid(stat.getGid());
            break;
        case FuseConstants.FUSE_SET_ATTR_SIZE:
            s.setSize(stat.getSize());
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

    public void access(FuseReq req, long ino, int mask) {
        Reply.err(req, 0);
    }

}
