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

import jlowfuse.FuseReq;
import jlowfuse.Reply;
import fuse.FileInfo;
import objectfs.Inode;
import objectfs.async.ObjectFsContext;

public class Write extends jlowfuse.async.tasks.Write<ObjectFsContext> {

	public Write(FuseReq req, long ino, ByteBuffer buf, long off, FileInfo fi) {
		super(req, ino, buf, off, fi);
	}
	
	public void run() {
        Inode inode = context.fs.getInodeByIno(ino);
        long written = inode.writeData(buf, off);
        Reply.write(req, written);
	}

}
