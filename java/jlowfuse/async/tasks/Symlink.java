
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Symlink {
	public void setSymlinkAttributes(FuseReq req, String link, long parent, String name);
}
