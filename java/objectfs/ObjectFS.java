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

import java.util.Hashtable;

import fuse.Stat;
import fuse.StatConstants;

public class ObjectFS {
	private Inode rootInode;

	private Hashtable<Long, Inode> inodeTable = new Hashtable<Long, Inode>();

	private void initializeFilesystemRoot() {
		rootInode = new Inode(null, "");
		rootInode.setParent(rootInode);
		inodeTable.put(1L, rootInode);

		Stat s = new Stat();
		s.setIno(rootInode.getIno());
		s.setNlink(3L);
		s.setMode(StatConstants.IFDIR | 0777);
		s.setUid(0);
		s.setGid(0);
		rootInode.setStat(s);
	}

	public ObjectFS() {
		initializeFilesystemRoot();
	}

	public Inode getInodeByIno(long ino) {
		return inodeTable.get(ino);
	}

	public Inode getChildInodeByName(Inode parent, String name) {
		for (Inode i : parent.getChildren()) {
			if (name.equals(i.getName())) {
				return i;
			}
		}
		return null;
	}

	public Inode createAndWireNewInode(Inode parent, String name) {
		Inode inode = new Inode(parent, name);
		wireInode(inode);
		return inode;
	}

	private void wireInode(Inode inode) {
		inodeTable.put(inode.getIno(), inode);

		Inode p = inode.getParent();
		p.addChild(inode);
		p.getStat().setNlink(p.getStat().getNlink() + 1);
	}

	public void removeInode(Inode inode) {
		Inode parent = inode.getParent();

		parent.getChildren().remove(inode);
		parent.getStat().setNlink(parent.getStat().getNlink() - 1);
		inode.setParent(null);
		inodeTable.remove(inode);
	}

}
