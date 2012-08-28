/*
 * Copyright (c) 2011 Marcel Lauhoff.
 * 
 * This file is part of jlowfuse.
 * 
 * jlowfuse is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * jlowfuse is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with jlowfuse.  If not, see <http://www.gnu.org/licenses/>.
 */

package objectfs.classic;

import fuse.*;
import jlowfuse.*;
import jlowfuse.classic.*;
import java.nio.ByteBuffer;

import objectfs.Inode;
import objectfs.ObjectFS;

public class ObjectFsOps extends ClassicLowlevelOps implements LowlevelOps{
	ObjectFS fs;
	
    public void init() {
    	fs = new ObjectFS();
    	
    }
    
    private void mkdirnod(FuseReq req, long parentIno, String name,
                          short fullMode, long nlink) {
        Inode parent = fs.getInodeByIno(parentIno);

        if (parent != null) {            
            Inode inode = fs.createAndWireNewInode(parent, name);
            
            Stat s = new Stat();
            s.setIno(inode.getIno());
            s.setNlink(nlink);
            s.setMode(fullMode);
            s.setUid(0);
            s.setGid(0);
            inode.setStat(s);
        


            Reply.entry(req, inode.getEntryParam());
        } else {
            Reply.err(req, Errno.EPERM);
        }
    }


    public void read(FuseReq req, long ino, long size, long off, FileInfo fi) {
        Inode inode = fs.getInodeByIno(ino);
        ByteBuffer buf = inode.getData();

        System.out.println("READ  " + buf + " off=" + off + " size=" + size);
        if (buf != null) {        
            Reply.byteBuffer(req, buf, off, size);
        } else {
	        buf = ByteBuffer.allocateDirect((int)size);            
            Reply.byteBuffer(req, buf, off, size);
        }
    }


    public void write(FuseReq req, long ino, ByteBuffer src, long off,
                      FileInfo fi) {
        Inode inode = fs.getInodeByIno(ino);
        long written = inode.writeData(src, off);
        Reply.write(req, written);
    }

    public void mkdir(FuseReq req, long parent, String name, short mode) {
        mkdirnod(req, parent, name, (short)(StatConstants.IFDIR | mode), 2);
    }
        
    public void mknod(FuseReq req, long parent, String name, short mode,
                      short rdev) {
        mkdirnod(req, parent, name, (short)(StatConstants.IFREG | mode), 1);
    }

    public void rmdir(FuseReq req, long parentIno, String name) {
        Inode parent = fs.getInodeByIno(parentIno);
        Inode child = fs.getChildInodeByName(parent, name);

        if (child != null) {
            fs.removeInode(child);            
            Reply.err(req, 0);
        } else {
            Reply.err(req, Errno.ENOENT);
        }
    }

    public void unlink(FuseReq req, long parent, String name) {
        rmdir(req, parent, name);        
    }    
    
    public void readdir(FuseReq req, long ino, long size, long off, FileInfo fi) {
        Inode inode = fs.getInodeByIno(ino);
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
        Inode parent = fs.getInodeByIno(ino);

        if (parent != null) {
            Inode child = fs.getChildInodeByName(parent, name);
            System.out.println(child);
            if (child != null) {
                Reply.entry(req, child.getEntryParam());
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
        Inode inode = fs.getInodeByIno(ino);
        
        if (inode != null) {
        	Reply.attr(req, inode.getStat(), 0.0);
        } else {
        	Reply.err(req, Errno.ENOENT);
        }

    }
    
    public void setattr(FuseReq req, long ino, Stat stat, int to_set,
                        FileInfo fi) {
        Inode inode = fs.getInodeByIno(ino);

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
