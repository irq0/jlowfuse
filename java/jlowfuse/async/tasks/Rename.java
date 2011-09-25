
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Rename {
	public void setRenameAttributes(FuseReq req, long parent, String name, long newparent, String newname);
}
