
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Bmap {
	public void attributes(FuseReq req, long ino, int blocksize, long idx);
}
