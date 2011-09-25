
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Link {
	public void setLinkAttributes(FuseReq req, long ino, long newparent, String newname);
}
