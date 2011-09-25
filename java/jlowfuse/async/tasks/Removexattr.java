
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Removexattr {
	public void setRemovexattrAttributes(FuseReq req, long ino, String name);
}
