
package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;

public interface Opendir {
	public void attributes(FuseReq req, long ino, FileInfo fi);
}
