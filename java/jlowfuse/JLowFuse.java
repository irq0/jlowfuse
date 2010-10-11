package jlowfuse;

import fuse.*;

public class JLowFuse {
    public native int init(Object opts);
    
    static {
        System.loadLibrary("jlowfuse");
    }
    
} 
