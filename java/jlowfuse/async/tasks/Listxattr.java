
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Listxattr {
	public void setListxattrAttributes(FuseReq req, long ino, int size);
}
