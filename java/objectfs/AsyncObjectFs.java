package objectfs;

import fuse.*;
import jlowfuse.*;
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
        
        TaskImplementations impls = new TaskImplementations();
        impls.setInit()
        
        
        SWIGTYPE_p_fuse_session sess = JLowFuse.asyncTasksNew(args, )

        Session.addChan(sess, chan);
        Session.loopSingle(sess);
        // Session.loopMulti(sess);

        Session.removeChan(chan);

        Session.destroy(sess);
        Session.exit(sess);

        Fuse.unmount(mountpoint, chan);        
    }
      
}
