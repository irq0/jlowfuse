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

import fuse.Errno;
import objectfs.Inode;
import objectfs.async.ObjectFsContext;
import jlowfuse.FuseReq;
import jlowfuse.Reply;

public class Rmdir extends jlowfuse.async.tasks.Rmdir<ObjectFsContext> {

	public Rmdir(FuseReq req, long parent, String name) {
		super(req, parent, name);
	}
	
	public void run() {
        Inode parentInode = context.fs.getInodeByIno(parent);
        Inode childInode = context.fs.getChildInodeByName(parentInode, name);

        if (childInode != null) {
            context.fs.removeInode(childInode);            
            Reply.err(req, 0);
        } else {
            Reply.err(req, Errno.ENOENT);
        }
	}

}
