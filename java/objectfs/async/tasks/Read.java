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

import java.nio.ByteBuffer;

import objectfs.Inode;
import objectfs.async.ObjectFsContext;
import jlowfuse.FuseReq;
import jlowfuse.Reply;
import fuse.FileInfo;

public class Read extends jlowfuse.async.tasks.Read<ObjectFsContext> {

	public Read(FuseReq req, long ino, long size, long off, FileInfo fi) {
		super(req, ino, size, off, fi);
	}
	
	public void run() {
        Inode inode = context.fs.getInodeByIno(ino);
        ByteBuffer buf = inode.getData();

        System.out.println("READ  " + buf + " off=" + off + " size=" + size);
        if (buf != null) {        
            Reply.byteBuffer(req, buf, off, size);
        } else {
	        buf = ByteBuffer.allocateDirect((int)size);            
            Reply.byteBuffer(req, buf, off, size);
        }	
	}

}
