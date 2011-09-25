
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Readlink {
	public void setReadlinkAttributes(FuseReq req, long ino);
}
