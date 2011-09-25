
package jlowfuse.async.tasks;

import java.nio.ByteBuffer;

import jlowfuse.FuseReq;

public interface Setxattr {
	public void attributes(FuseReq req, long ino, String name, ByteBuffer value, int flags);
}
