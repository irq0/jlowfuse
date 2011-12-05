package jlowfuse;

import fuse.FuseContext;
import fuse.FuseRequest;
import fuse.SWIGTYPE_p_fuse_req;

public class FuseReq extends SWIGTYPE_p_fuse_req {
	public FuseReq(long ptr) {
		super(ptr, false);
	}

	long getCPtr() {
		return SWIGTYPE_p_fuse_req.getCPtr(this);
	}

	@Override
	public String toString() {
		return "fuse_req_t: ptr=0x" + Long.toHexString(getCPtr(this));
	}

	public FuseContext getContext() {
		return FuseRequest.getContext(this);
	}
}
