
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Read {
	public void attributes(FuseReq req, long ino, long size, long off, FileInfo fi);
}
