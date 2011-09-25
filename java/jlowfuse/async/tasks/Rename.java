
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Rename {
	public void attributes(FuseReq req, long parent, String name, long newparent, String newname);
}
