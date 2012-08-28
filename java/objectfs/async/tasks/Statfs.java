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

import fuse.StatVFS;
import objectfs.async.ObjectFsContext;
import jlowfuse.FuseReq;
import jlowfuse.Reply;

public class Statfs extends jlowfuse.async.tasks.Statfs<ObjectFsContext> {

	public Statfs(FuseReq req, long ino) {
		super(req, ino);
	}
	
	public void run() {
        StatVFS s = new StatVFS();
        
        s.setBsize(1);
        s.setFrsize(1024);
        s.setBfree(19);
        s.setBlocks(42);
        s.setFiles(23);
        s.setFavail(5);
        
        Reply.statfs(req, s);
	}

}
