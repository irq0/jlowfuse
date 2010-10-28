package jlowfuse;

import fuse.*;

public class JLowFuseArgs extends FuseArgs {
    private static native long makeFuseArgs(String[] args);
    public static JLowFuseArgs parseCommandline(String[] args) {
        long args_l = JLowFuseArgs.makeFuseArgs(args);
        return new JLowFuseArgs(args_l);
    }

    public JLowFuseArgs(long ptr) {
        super(ptr, true);
    }
    public JLowFuseArgs() {
        super();
    }

    public String toString() {
        return "lowlevel args ptr:" + super.getCPtr(this);
    }

    static {
        System.loadLibrary("jlowfuse");
    }

}
