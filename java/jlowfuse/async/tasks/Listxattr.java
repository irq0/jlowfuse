
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Listxattr {
	public void attributes(FuseReq req, long ino, int size);
}
