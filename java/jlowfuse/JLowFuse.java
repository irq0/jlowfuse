package jlowfuse;

import fuse.*;
import java.nio.ByteBuffer;

public class JLowFuse {
    public native int init(Object opts);
    private native long setOps(AbstractLowlevelOpts ops);

    public SWIGTYPE_p_fuse_session lowlevelNew(AbstractLowlevelOpts ops_object) {
        long ops_l;
        ops_l = setOps(ops_object);

        System.out.println(ops_l);
        
        FuseLowlevelOps ops = new FuseLowlevelOps(ops_l);

        System.out.println(ops);
        
        return fuse.fuse_lowlevel_new(new FuseArgs(),
                               ops, 144 , null); /* 34 = # fuse ops */
    }    
    static {
        System.loadLibrary("jlowfuse");
    }
    
} 
