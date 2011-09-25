
package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;

public interface Open {
	public void setOpenAttributes(FuseReq req, long ino, FileInfo fi);
}
