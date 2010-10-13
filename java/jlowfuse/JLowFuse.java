package jlowfuse;

import fuse.*;

public class JLowFuse {
    public native int init(Object opts);
    private native long setOps(AbstractLowlevelOpts ops);

    public Session lowlevelNew(AbstractLowlevelOpts ops_object) {
        long ops_l;
        ops_l = setOps(ops_object);

        FuseLowlevelOps ops = new FuseLowlevelOps(ops_l);

        fuse.fuse_lowlevel_new(null, ops, 34 , null); /* 34 = # fuse ops */
        return null;
    }
    
    static {
        System.loadLibrary("jlowfuse");
    }
    
} 
