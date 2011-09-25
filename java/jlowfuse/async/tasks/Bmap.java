
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Bmap {
	public void setBmapAttributes(FuseReq req, long ino, int blocksize, long idx);
}
