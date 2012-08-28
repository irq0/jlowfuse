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
