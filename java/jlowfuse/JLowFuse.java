package jlowfuse;

import fuse.*;

public class JLowFuse {
    public native int init(Object opts);
    private native SWIGTYPE_p_fuse_lowlevel_ops setOpsObject(AbstractLowlevelOpts ops);

    public Session lowlevelNew(AbstractLowlevelOpts ops) {
        SWIGTYPE_p_fuse_lowlevel_ops ops_p;

        ops_p = setOpsObject(ops);

        fuse_lowlevel_new(null, ops_p, sizeof(ops_p), null); // will not work :(
    }
    
    static {
        System.loadLibrary("jlowfuse");
    }
    
} 
