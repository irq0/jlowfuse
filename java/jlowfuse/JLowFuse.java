package jlowfuse;

import fuse.*;

public class JLowFuse {
    public native int init(Object opts);
    private static native long setOps(AbstractLowlevelOpts ops);
    private static native long makeFuseArgs(String[] args);

    
    public static SWIGTYPE_p_fuse_session lowlevelNew(FuseArgs args, AbstractLowlevelOpts ops_object) {
        long ops_l;
        ops_l = JLowFuse.setOps(ops_object);

        FuseLowlevelOps ops = new FuseLowlevelOps(ops_l);

        return fuse.fuse_lowlevel_new(args,
                                      ops, 144 , null); /* 144 = sizeof(fuse ops) */
    }

    public static FuseArgs parseCommandline(String[] args) {
        long args_l = JLowFuse.makeFuseArgs(args);
        return new FuseArgs(args_l);
    }
    
    static {
        System.loadLibrary("jlowfuse");
    }
    
} 
