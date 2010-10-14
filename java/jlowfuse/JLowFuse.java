package jlowfuse;

import fuse.*;

public class JLowFuse {
    public native int init(Object opts);
    private static native long setOps(AbstractLowlevelOpts ops);

    public static SWIGTYPE_p_fuse_session lowlevelNew(AbstractLowlevelOpts ops_object) {
        long ops_l;
        ops_l = setOps(ops_object);

        FuseLowlevelOps ops = new FuseLowlevelOps(ops_l);
        
        return fuse.fuse_lowlevel_new(new FuseArgs(),
                                      ops, 144 , null); /* 144 = sizeof(fuse ops) */
    }
    
    static {
        System.loadLibrary("jlowfuse");
    }
    
} 
