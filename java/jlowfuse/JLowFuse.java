package jlowfuse;

import fuse.*;

public class JLowFuse {
    public native int init(Object opts);
    private static native long setOps(LowlevelOpsProxy ops);

    /**
     * Establish new lowlevel session.
     * Registers `LowlevelOps` in fuse c api and calles fuse_lowlevel_new
     *
     * @param args Fuse Args eg. Commandline
     * @param ops  LowlevelOps instance - implements filesystem operations
     * @return Opaque pointer to fuse_session type
     */
    public static SWIGTYPE_p_fuse_session lowlevelNew(FuseArgs args, LowlevelOps ops) {
        LowlevelOpsProxy proxy = new LowlevelOpsProxy();
        proxy.register(ops);
        
        long ops_p = JLowFuse.setOps(proxy);
        FuseLowlevelOps ops_f = new FuseLowlevelOps(ops_p);

        return fuse.fuse_lowlevel_new(args,
                                      ops_f, 144 , null); /* 144 = sizeof(fuse ops) */
    }

    
    static {
        System.loadLibrary("jlowfuse");
    }
    
} 
