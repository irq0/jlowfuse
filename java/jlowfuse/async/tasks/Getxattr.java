
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Getxattr {
	public void setGetxattrAttributes(FuseReq req, long ino, String name, int size);
}
