
package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;

public interface Create {
	public void setCreateAttributes(FuseReq req, long parent, String name, short mode, FileInfo fi);
}
