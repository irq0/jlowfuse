package jlowfuse;

import fuse.*;
import java.util.concurrent.ExecutorService;

import jlowfuse.async.AsyncLowlevelOps;
import jlowfuse.async.AsyncTasksOpsProxy;
import jlowfuse.async.TaskImplementations;
import jlowfuse.classic.*;

public class JLowFuse {
    public native int init(Object opts);
    private static native long setOps(OpsProxy ops, ThreadGroup tgroup);

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

        return Fuse.lowlevelNew(args, ops_f, 144 , null); /* 144 = sizeof(fuse ops) */
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
     * @param taskImplementations class with all the implementations for the operations
     * @return Opaque pointer to fuse_session type
     */
	public static SWIGTYPE_p_fuse_session asyncTasksNew(FuseArgs args, 
			TaskImplementations taskImplementations, ExecutorService service) {
		
		LowlevelOpsProxy proxy = new LowlevelOpsProxy();		
		AsyncLowlevelOps ops = new AsyncLowlevelOps(taskImplementations, service);		
		
		proxy.register(ops);
		
		long ops_p = JLowFuse.setOps(proxy, null);
		FuseLowlevelOps ops_f = new FuseLowlevelOps(ops_p);
		
	    return Fuse.lowlevelNew(args, ops_f, 144, null);
    }
    
    static {
        System.loadLibrary("jlowfuse");
    }	
} 
