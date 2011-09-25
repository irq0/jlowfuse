
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Unlink {
	public void setUnlinkAttributes(FuseReq req, long parent, String name);
}
