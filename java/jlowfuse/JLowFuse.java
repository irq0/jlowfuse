package jlowfuse;

import fuse.*;

public class JLowFuse {
    public native int init(Object opts);
    private static native long setOps(AbstractLowlevelOps ops);
    
    public static SWIGTYPE_p_fuse_session lowlevelNew(FuseArgs args, AbstractLowlevelOps ops_object) {
        long ops_l;
        ops_l = JLowFuse.setOps(ops_object);

        FuseLowlevelOps ops = new FuseLowlevelOps(ops_l);

        return fuse.fuse_lowlevel_new(args,
                                      ops, 144 , null); /* 144 = sizeof(fuse ops) */
    }

    
    static {
        System.loadLibrary("jlowfuse");
    }
    
} 
