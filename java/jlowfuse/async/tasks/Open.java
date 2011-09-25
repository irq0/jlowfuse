
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Open {
	public void attributes(FuseReq req, long ino, FileInfo fi);
}
