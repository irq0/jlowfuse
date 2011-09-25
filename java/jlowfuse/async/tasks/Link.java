
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Link {
	public void attributes(FuseReq req, long ino, long newparent, String newname);
}
