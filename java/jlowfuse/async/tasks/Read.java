
package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;

public interface Read {
	public void setReadAttributes(FuseReq req, long ino, long size, long off, FileInfo fi);
}
