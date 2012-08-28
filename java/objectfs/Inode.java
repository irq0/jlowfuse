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

package objectfs;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

import fuse.EntryParam;
import fuse.Stat;

public class Inode {
	private Inode parent;

	private LinkedList<Inode> children = new LinkedList<Inode>();

	private long ino;

	private String name;

	private Stat stat;

	private ByteBuffer data;

	private static long nextIno = 1;

	public static long getNextIno() {
		return Inode.nextIno++;
	}

	public Inode(long ino, Inode parent, String name) {
		this.parent = parent;
		this.ino = ino;
		this.name = name;
	}

	public Inode(Inode parent, String name) {
		this(Inode.getNextIno(), parent, name);
	}

	public void setData(ByteBuffer data) {
		this.data = data;
	}

	public ByteBuffer getData() {
		return this.data;
	}

	public void setParent(Inode parent) {
		this.parent = parent;
	}

	public Inode getParent() {
		return this.parent;
	}

	public long getIno() {
		return this.ino;
	}

	public String getName() {
		return this.name;
	}

	public Stat getStat() {
		return this.stat;
	}

	public void setStat(Stat stat) {
		this.stat = stat;
	}

	public List<Inode> getChildren() {
		return this.children;
	}

	public void addChild(Inode child) {
		this.children.add(child);
	}

	public EntryParam getEntryParam() {
		EntryParam e = new EntryParam();
		e.setGeneration(23);
		e.setIno(getIno());
		e.setAttr_timeout(0.0);
		e.setEntry_timeout(0.0);
		e.setAttr(getStat());
		return e;
	}

	private void updateDataSizeInStat() {
		getStat().setSize(getData().capacity());
	}

	@Override
	public String toString() {
		return "ObjectFs.Inode(ino=" + ino + ";name=" + name + ")";
	}

	public long writeData(ByteBuffer src, long off) {
		ByteBuffer dst = getData();

		if (dst == null) { // uninitialized
			ByteBuffer buf = ByteBuffer.allocateDirect(Math.max(src.capacity()
					+ (int) off, 4096));
			buf.position((int) off);
			buf.put(src);

			dst = buf;
			setData(buf);
		} else if (dst.capacity() < (src.capacity() + off)) { // to small
			System.out.println(dst + "    " + src);
			ByteBuffer buf = ByteBuffer
					.allocateDirect(Math.max(dst.capacity() + src.capacity()
							+ (int) off, dst.capacity() + 1024 * 1024));
			buf.put(dst);
			buf.position((int) off);
			buf.put(src);

			dst = buf;
			setData(buf);
		} else {
			dst.position((int) off);
			dst.put(src);
		}

		dst.rewind();
		updateDataSizeInStat();
		return src.capacity();

	}
}
