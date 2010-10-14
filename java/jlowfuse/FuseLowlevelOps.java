package jlowfuse;

import fuse.*;

public class FuseLowlevelOps extends SWIGTYPE_p_fuse_lowlevel_ops {
    public FuseLowlevelOps(long ptr) {
        super(ptr, true);
    }

    public String toString() {
        return "lowlevel opts ptr:" + super.getCPtr(this);
    }
}