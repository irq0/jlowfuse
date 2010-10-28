package objectfs;

import fuse.*;
import jlowfuse.*;

public class ObjectFs {
    static {
        System.loadLibrary("jlowfuse");
    }

    public static void main(String[] strargs) {

        JLowFuseArgs args = JLowFuseArgs.parseCommandline(strargs);
        
        SWIGTYPE_p_fuse_chan chan = Fuse.mount("/mnt1", args);
        SWIGTYPE_p_fuse_session sess = JLowFuse.lowlevelNew(args, new ObjectFsOps());

        Session.addChan(sess, chan);
        Session.loopSingle(sess);
        // Session.loopMulti(sess);

        Session.removeChan(chan);

        Session.destroy(sess);
        Session.exit(sess);

        Fuse.unmount("/mnt1", chan);
        
    }
      
}

