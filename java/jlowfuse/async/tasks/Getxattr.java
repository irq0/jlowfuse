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

import jlowfuse.FuseReq;
import jlowfuse.async.Context;

public class Getxattr<CTX extends Context> extends FilesystemOperation<CTX> {
	protected long ino;
	protected String name;
	protected int size;
	
	public Getxattr(FuseReq req, long ino, String name, int size) {
		super(req);
		this.ino = ino;
		this.name = name;
		this.size = size;
	}
	
	public String toString() {
		return new StringBuilder(super.toString())
			.append(" ino=")
			.append(ino)
			.append(" size=")
			.append(size)
			.append(" name=")
			.append(name)	
			.toString();
	}}
