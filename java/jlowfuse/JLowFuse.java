package jlowfuse;

import fuse.*;
import java.util.concurrent.ExecutorService;
import jlowfuse.async.TaskImplementations;

public class JLowFuse {
    public native int init(Object opts);
    private static native long setOps(LowlevelOpsProxy ops, ThreadGroup tgroup);

    /**
     * Establish new lowlevel session.  Registers `LowlevelOps` in
     * fuse c api and calles fuse_lowlevel_new
     *
     * @param args Fuse Args eg. Commandline
     * @param ops  LowlevelOps instance - implements filesystem operations
     * @param tgroup java threads created by JNI AttachCurrentThreadAsDaemon
     * @return Opaque pointer to fuse_session type
     */
    public static SWIGTYPE_p_fuse_session lowlevelNew(FuseArgs args, LowlevelOps ops, ThreadGroup tgroup) {
        LowlevelOpsProxy proxy = new LowlevelOpsProxy();
        proxy.register(ops);
        
        long ops_p = JLowFuse.setOps(proxy, tgroup);
        FuseLowlevelOps ops_f = new FuseLowlevelOps(ops_p);

        return Fuse.lowlevelNew(args,
                                ops_f, 144 , null); /* 144 = sizeof(fuse ops) */
    }

    /**
     * Establish new lowlevel session.  Registers `LowlevelOps` in
     * fuse c api and calles fuse_lowlevel_new
     *
     * @param args Fuse Args eg. Commandline
     * @param ops  LowlevelOps instance - implements filesystem operations
     * @return Opaque pointer to fuse_session type
     */
    public static SWIGTYPE_p_fuse_session lowlevelNew(FuseArgs args, LowlevelOps ops) {
	    return lowlevelNew(args, ops, null);
    }
    
    /**
     * Create new lowlevel session using the concurrency api.
     *
     * @param args Fuse Args eg. Commandline
     * @param executor handles Tasks
     * @return Opaque pointer to fuse_session type
     */
	public static SWIGTYPE_p_fuse_session asyncTasksNew(FuseArgs args, TaskImplementations impls, ExecutorService service) {
	    return null;
    }
    
    static {
        System.loadLibrary("jlowfuse");
    }	
} 
