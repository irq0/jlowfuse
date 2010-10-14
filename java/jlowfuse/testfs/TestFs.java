package jlowfuse.testfs;

import fuse.*;
import jlowfuse.*;
import java.util.*;

public class TestFs {
    static {
        System.loadLibrary("jlowfuse");
    }

    public static void main(String[] strargs) {

        FuseArgs args = FuseArgs.parseCommandline(strargs);
        
        SWIGTYPE_p_fuse_chan chan = fuse.fuse_mount("/mnt1", args);        
        SWIGTYPE_p_fuse_session sess = JLowFuse.lowlevelNew(args, new TestFsOpts());

        //        fuse.fuse_set_signal_handlers(sess);
        fuse.fuse_session_add_chan(sess, chan);
        int err = fuse.fuse_session_loop(sess);

        //        fuse.fuse_remove_signal_handlers(sess);
        fuse.fuse_session_remove_chan(chan);

        fuse.fuse_session_destroy(sess);
        fuse.fuse_session_exit(sess);

        fuse.fuse_unmount("/mnt1", chan);
        
    }
      
}

