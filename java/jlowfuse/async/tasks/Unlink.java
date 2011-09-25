
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Unlink {
	public void attributes(FuseReq req, long parent, String name);
}
