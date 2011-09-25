
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Mkdir {
	public void attributes(FuseReq req, long parent, String name, short mode);
}
