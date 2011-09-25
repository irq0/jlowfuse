
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Access {
	public void setAccessAttributes(FuseReq req, long ino, int mask);
}
