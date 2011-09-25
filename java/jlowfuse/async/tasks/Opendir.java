
package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;

public interface Opendir {
	public void setOpendirAttributes(FuseReq req, long ino, FileInfo fi);
}
