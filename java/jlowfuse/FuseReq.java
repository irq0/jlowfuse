package jlowfuse;

import fuse.*;

public class FuseReq extends SWIGTYPE_p_fuse_req_t {
    public FuseReq(long ptr) {
        super(ptr, true);
    }
    public FuseReq() {
        super();
    }

    public String toString() {
        return "fuse_req_t: ptr=" + getCPtr(this);
    }
}                            
