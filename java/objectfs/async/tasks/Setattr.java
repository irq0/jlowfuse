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

import objectfs.Inode;
import objectfs.ObjectFS;
import objectfs.async.ObjectFsContext;
import jlowfuse.FuseReq;
import jlowfuse.Reply;
import fuse.Errno;
import fuse.FileInfo;
import fuse.FuseConstants;
import fuse.Stat;

public class Setattr extends jlowfuse.async.tasks.Setattr<ObjectFsContext> {

	public Setattr(FuseReq req, long ino, Stat attr, int to_set, FileInfo fi) {
	    super(req, ino, attr, to_set, fi);
    }

	public void run() {
		ObjectFS fs = context.fs;
        Inode inode = fs.getInodeByIno(ino);

        if (inode == null) {
            Reply.err(req, Errno.ENOENT);
        }

        Stat s = inode.getStat();

        switch(to_set) {
        case FuseConstants.FUSE_SET_ATTR_MODE:
            s.setMode(attr.getMode());
            break;
        case FuseConstants.FUSE_SET_ATTR_UID:
            s.setUid(attr.getUid());
            break;
        case FuseConstants.FUSE_SET_ATTR_GID:
            s.setGid(attr.getGid());
            break;
        case FuseConstants.FUSE_SET_ATTR_SIZE:
            s.setSize(attr.getSize());
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

}
