
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Forget {
	public void attributes(FuseReq req, long ino, long nlookup);
}
