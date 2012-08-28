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

package jlowfuse;

import fuse.Stat;
import fuse.FileInfo;
import java.nio.ByteBuffer;

public interface LowlevelOps {
    public void init();
    public void destroy();
    public void lookup(FuseReq req, long parent, String name);
    public void forget(FuseReq req, long ino, long nlookup);
    public void getattr(FuseReq req, long ino, FileInfo fi);
    public void setattr(FuseReq req, long ino, Stat attr, int to_set, FileInfo fi);
    public void readlink(FuseReq req, long ino);
    public void mknod(FuseReq req, long parent, String name, short mode, short rdev);
    public void mkdir(FuseReq req, long parent, String name, short mode);
    public void unlink(FuseReq req, long parent, String name);
    public void rmdir(FuseReq req, long parent, String name);
    public void symlink(FuseReq req, String link, long parent, String name);
    public void rename(FuseReq req, long parent, String name, long newparent, String newname);
    public void link(FuseReq req, long ino, long newparent, String newname);
    public void open(FuseReq req, long ino, FileInfo fi);
    public void read(FuseReq req, long ino, long size, long off, FileInfo fi);
    public void write(FuseReq req, long ino, ByteBuffer buf, long off, FileInfo fi);
    public void flush(FuseReq req, long ino, FileInfo fi);
    public void release(FuseReq req, long ino, FileInfo fi);
    public void fsync(FuseReq req, long ino, int datasync, FileInfo fi);
    public void opendir(FuseReq req, long ino, FileInfo fi);
    public void readdir(FuseReq req, long ino, long size, long off, FileInfo fi);
    public void releasedir(FuseReq req, long ino, FileInfo fi);
    public void fsyncdir(FuseReq req, long ino, int datasync, FileInfo fi);
    public void statfs(FuseReq req, long ino);
    public void setxattr(FuseReq req, long ino, String name, ByteBuffer value, int flags);
    public void getxattr(FuseReq req, long ino, String name, int size);
    public void listxattr(FuseReq req, long ino, int size);
    public void removexattr(FuseReq req, long ino, String name);
    public void access(FuseReq req, long ino, int mask);
    public void create(FuseReq req, long parent, String name, short mode, FileInfo fi);
    public void bmap(FuseReq req, long ino, int blocksize, long idx);
}
