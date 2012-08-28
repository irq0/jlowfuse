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

package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Fsync<CTX extends Context> extends FilesystemOperation<CTX> {
	protected long ino;
	protected int datasync;
	protected FileInfo fi;
	
	public Fsync(FuseReq req, long ino, int datasync, FileInfo fi) {
		super(req);
		this.ino = ino;
		this.datasync = datasync;
		this.fi = fi;
	}
	
	public String toString() {
		return new StringBuilder(super.toString())
			.append(" ino=")
			.append(ino)
			.toString();
	}
}
