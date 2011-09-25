
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Forget {
	public void setForgetAttributes(FuseReq req, long ino, long nlookup);
}
