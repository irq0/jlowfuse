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

package jlowfuse;

import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BufferManager {
	private BlockingQueue<ByteBuffer> available;
	private int nbuf;
	private int capacity;


	private BufferManager() {
	}

	public static BufferManager create(int nbuf, int capacity) {
		BufferManager man = new BufferManager();
		man.nbuf = nbuf;
		man.capacity = capacity;

		man.setupStore();
		man.createBuffer();

		return man;
	}


	private void setupStore() {
		available = new ArrayBlockingQueue<ByteBuffer>(nbuf);
	}


	private void createBuffer() {
		for (int i=0; i<nbuf; i++) {
			ByteBuffer buf = ByteBuffer.allocateDirect(capacity);
			available.add(buf);
		}

	}

	public ByteBuffer take() {
		ByteBuffer buf;

		do {
			try {
				buf = available.take();
			} catch (InterruptedException e) {
				buf = null;
			}
		} while (buf == null);

		return buf;
	}

	public void free(ByteBuffer buf) {
		boolean freed = false;
		do {
			try {
				available.put(buf);
				freed = true;
			} catch (InterruptedException e) {
				freed = false;
			}
		} while (!freed);
	}

	public String toString() {
		return new StringBuilder()
			.append("BufferManager")
			.append("[")
			.append("buf-cap=").append(capacity)
			.append(",#max=").append(nbuf)
			.append(",#available=").append(available.size())
			.append("]")
			.toString();
	}
}
