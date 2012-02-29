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
		boolean put = false;
		do {
			try {
				available.put(buf);
				put = true;
			} catch (InterruptedException e) {
				put = false;
			}
		} while (!put);
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
