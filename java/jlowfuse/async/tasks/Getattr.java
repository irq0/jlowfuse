
package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;

public interface Getattr {
	public void attributes(FuseReq req, long ino, FileInfo fi);
}
