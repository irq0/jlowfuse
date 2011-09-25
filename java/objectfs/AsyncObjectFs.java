package objectfs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import objectfs.async.tasks.Init;
import fuse.*;
import jlowfuse.*;
import jlowfuse.async.DefaultTaskImplementations;
import jlowfuse.async.TaskImplementations;

public class AsyncObjectFs {
    static {
        System.loadLibrary("jlowfuse");
    }

    public static void main(String[] strArgs) {
	    String[] fuseArgs = {"-o foo,subtype=objfs", "-d"};
	    String mountpoint = strArgs[0];	 
        JLowFuseArgs args = JLowFuseArgs.parseCommandline(fuseArgs);
        
        SWIGTYPE_p_fuse_chan chan = Fuse.mount(mountpoint, args);
        
        DefaultTaskImplementations impls = new DefaultTaskImplementations();
        impls.initImpl = Init.class;
        
        ExecutorService service = new ThreadPoolExecutor(10,
        		20, 5, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
        
        SWIGTYPE_p_fuse_session sess = JLowFuse.asyncTasksNew(args, impls, service);

        Session.addChan(sess, chan);
        Session.loopSingle(sess);

        Session.removeChan(chan);

        Session.destroy(sess);
        Session.exit(sess);

        Fuse.unmount(mountpoint, chan);        
    }
      
}
