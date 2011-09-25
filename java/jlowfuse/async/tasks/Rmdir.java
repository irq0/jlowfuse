
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Rmdir {
	public void setRmdirAttributes(FuseReq req, long parent, String name);
}
