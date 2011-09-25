
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Lookup {
	public void attributes(FuseReq req, long parent, String name);
}
