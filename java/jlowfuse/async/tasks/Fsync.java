
package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;

public interface Fsync {
	public void attributes(FuseReq req, long ino, int datasync, FileInfo fi);
}
