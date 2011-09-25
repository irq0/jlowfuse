
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Access {
	public void attributes(FuseReq req, long ino, int mask);
}
