
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Statfs {
	public void attributes(FuseReq req, long ino);
}
