
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Release {
	public void attributes(FuseReq req, long ino, FileInfo fi);
}
