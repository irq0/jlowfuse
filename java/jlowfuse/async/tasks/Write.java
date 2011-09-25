
package jlowfuse.async.tasks;

import java.nio.ByteBuffer;

import fuse.FileInfo;
import jlowfuse.FuseReq;

public interface Write {
	public void attributes(FuseReq req, long ino, ByteBuffer buf, long off, FileInfo fi);
}
