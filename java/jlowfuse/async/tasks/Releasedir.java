
package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;

public interface Releasedir {
	public void setReleasedirAttributes(FuseReq req, long ino, FileInfo fi);
}
