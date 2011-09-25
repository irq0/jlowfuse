
package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;

public interface Fsyncdir {
	public void setFsyncdirAttributes(FuseReq req, long ino, int datasync, FileInfo fi);
}
