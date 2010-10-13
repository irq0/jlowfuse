package jlowfuse;

import fuse.*;

public class JLowFuse {
    public native int init(Object opts);
    private native long setOps(AbstractLowlevelOpts ops);

    public Session lowlevelNew(AbstractLowlevelOpts ops) {
        long ops_p;

        ops_p = setOps(ops);

        //        fuse_lowlevel_new(null, ops_p, sizeof(ops_p), null); // will not work :(
        return null;
    }
    
    static {
        System.loadLibrary("jlowfuse");
    }
    
} 
