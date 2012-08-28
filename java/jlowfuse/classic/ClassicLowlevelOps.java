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

package jlowfuse.classic;

import fuse.Stat;
import fuse.StatVFS;
import fuse.FileInfo;
import fuse.Errno;
import jlowfuse.FuseReq;
import jlowfuse.LowlevelOps;
import jlowfuse.Reply;
import java.nio.ByteBuffer;


public abstract class ClassicLowlevelOps implements LowlevelOps {
    public void init() {
    }

    public void destroy() {
    }

    public void lookup(FuseReq req, long parent, String name) {
	    Reply.err(req, Errno.ENOSYS);
    }

    public void forget(FuseReq req, long ino, long nlookup) {
        Reply.none(req);
    }

    public void getattr(FuseReq req, long ino, FileInfo fi) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void setattr(FuseReq req, long ino, Stat attr, int to_set,
                        FileInfo fi) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void readlink(FuseReq req, long ino) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void mknod(FuseReq req, long parent, String name, short mode, short rdev) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void mkdir(FuseReq req, long parent, String name, short mode) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void unlink(FuseReq req, long parent, String name) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void rmdir(FuseReq req, long parent, String name) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void symlink(FuseReq req, String link, long parent, String name) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void rename(FuseReq req, long parent, String name,
                long newparent, String newname) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void link(FuseReq req, long ino, long newparent, String newname) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void open(FuseReq req, long ino, FileInfo fi) {
        Reply.open(req, fi);
    }

    public void read(FuseReq req, long ino, long size, long off, FileInfo fi) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void write(FuseReq req, long ino, ByteBuffer buf, long off,
                      FileInfo fi) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void flush(FuseReq req, long ino, FileInfo fi) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void release(FuseReq req, long ino, FileInfo fi) {
        Reply.err(req, 0);
    }

    public void fsync(FuseReq req, long ino, int datasync, FileInfo fi) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void opendir(FuseReq req, long ino, FileInfo fi) {
        Reply.open(req, fi);
    }

    public void readdir(FuseReq req, long ino, long size, long off, FileInfo fi) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void releasedir(FuseReq req, long ino, FileInfo fi) {
        Reply.err(req, 0);
    }

    public void fsyncdir(FuseReq req, long ino, int datasync,
                         FileInfo fi) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void statfs(FuseReq req, long ino) {
        StatVFS stat = new StatVFS();
        stat.setNamemax(255);
        stat.setBsize(512);

        Reply.statfs(req, stat);
    }

    public void setxattr(FuseReq req, long ino, String name,
                  ByteBuffer value, int flags) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void getxattr(FuseReq req, long ino, String name, int size) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void listxattr(FuseReq req, long ino, int size) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void removexattr(FuseReq req, long ino, String name) {
        Reply.err(req, Errno.ENOSYS);
    }

    public void access(FuseReq req, long ino, int mask) {
        Reply.err(req, Errno.ENOSYS);
    }

	public void create(FuseReq req, long parent, String name, short mode, FileInfo fi) {
        Reply.err(req, Errno.ENOSYS);
    }

    /*    
    public void getlk(long ino, Flock lock) {
    }

    public void setlk(long ino, Flock lock, int sleep) {
    }
    */

    public void bmap(FuseReq req, long ino, int blocksize, long idx) {
        Reply.err(req, Errno.ENOSYS);
    }

    /*
    public void ioctl(long ino, int cmd, ByteBuffer arg,
    */

    /*
    public void poll(long ino, PollHandle ph) {
    }
    */
}

