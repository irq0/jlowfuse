
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Mkdir {
	public void setMkdirAttributes(FuseReq req, long parent, String name, short mode);
}
