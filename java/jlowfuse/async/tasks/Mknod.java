
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Mknod {
	public void setMknodAttributes(FuseReq req, long parent, String name, short mode, short rdev);
}
