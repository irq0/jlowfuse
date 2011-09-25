
package jlowfuse.async.tasks;

import fuse.FileInfo;
import fuse.Stat;
import jlowfuse.FuseReq;

public interface Setattr {
	public void attributes(FuseReq req, long ino, Stat attr, int to_set, FileInfo fi);
}
