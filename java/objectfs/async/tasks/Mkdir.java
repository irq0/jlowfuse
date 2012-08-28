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
import fuse.Stat;
import fuse.StatConstants;
import objectfs.Inode;
import objectfs.async.ObjectFsContext;
import jlowfuse.FuseReq;
import jlowfuse.Reply;

public class Mkdir extends jlowfuse.async.tasks.Mkdir<ObjectFsContext> {

	public Mkdir(FuseReq req, long parent, String name, short mode) {
		super(req, parent, name, mode);
	}

	public void run() {
        Inode parentInode = context.fs.getInodeByIno(parent);

        if (parentInode != null) {            
            Inode inode = context.fs.createAndWireNewInode(parentInode, name);
            Stat s = new Stat();
            s.setIno(inode.getIno());
            s.setNlink(2);
            s.setMode((short)(StatConstants.IFDIR | mode));
            s.setUid(0);
            s.setGid(0);
            inode.setStat(s);
        
            Reply.entry(req, inode.getEntryParam());
        } else {
            Reply.err(req, Errno.EPERM);
        }
	}
}
