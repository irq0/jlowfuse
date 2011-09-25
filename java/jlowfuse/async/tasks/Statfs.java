
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Statfs {
	public void setStatfsAttributes(FuseReq req, long ino);
}
