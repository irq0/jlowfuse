
package jlowfuse.async.tasks;

import java.nio.ByteBuffer;

import jlowfuse.FuseReq;

public interface Setxattr {
	public void setSetxattrAttributes(FuseReq req, long ino, String name, ByteBuffer value, int flags);
}
