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
import jlowfuse.FuseReq;
import jlowfuse.Reply;
import objectfs.Inode;
import objectfs.async.ObjectFsContext;

public class Lookup extends jlowfuse.async.tasks.Lookup<ObjectFsContext> {

	public Lookup(FuseReq req, long parent, String name) {
		super(req, parent, name);
	}

	public void run() {
        Inode parentInode = context.fs.getInodeByIno(parent);

        if (parentInode != null) {
            Inode child = context.fs.getChildInodeByName(parentInode, name);
            System.out.println(child);
            if (child != null) {
                Reply.entry(req, child.getEntryParam());
            } else {
                Reply.err(req, Errno.ENOENT);
            }
        }		
	}
}
