
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Readlink {
	public void attributes(FuseReq req, long ino);
}
