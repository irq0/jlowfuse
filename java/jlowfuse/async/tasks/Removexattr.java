
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Removexattr {
	public void attributes(FuseReq req, long ino, String name);
}
