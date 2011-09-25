
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface Lookup {
	public void setLookupAttributes(FuseReq req, long parent, String name);
}
