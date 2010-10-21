package objectfs;

import fuse.*;
import jlowfuse.*;

public class ObjectFs {
    static {
        System.loadLibrary("jlowfuse");
    }

    public static void main(String[] strargs) {

        FuseArgs args = FuseArgs.parseCommandline(strargs);
        
        SWIGTYPE_p_fuse_chan chan = fuse.fuse_mount("/mnt1", args);
        SWIGTYPE_p_fuse_session sess = JLowFuse.lowlevelNew(args, new ObjectFsOps());

        //        fuse.fuse_set_signal_handlers(sess);
        fuse.fuse_session_add_chan(sess, chan);
        int err = fuse.fuse_session_loop(sess);
        //        int err = fuse.fuse_session_loop_mt(sess);


        //        fuse.fuse_remove_signal_handlers(sess);
        fuse.fuse_session_remove_chan(chan);

        fuse.fuse_session_destroy(sess);
        fuse.fuse_session_exit(sess);

        fuse.fuse_unmount("/mnt1", chan);
        
    }
      
}

