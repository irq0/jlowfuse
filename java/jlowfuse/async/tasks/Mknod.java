
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Mknod {
	public void attributes(FuseReq req, long parent, String name, short mode, short rdev);
}
