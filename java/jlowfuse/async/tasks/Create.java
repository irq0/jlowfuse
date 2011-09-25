
package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;

public interface Create {
	public void attributes(FuseReq req, long parent, String name, short mode, FileInfo fi);
}
