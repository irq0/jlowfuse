package jlowfuse;

import fuse.*;

public class FuseArgs extends fuse_args {
    private static native long makeFuseArgs(String[] args);
    public static FuseArgs parseCommandline(String[] args) {
        long args_l = FuseArgs.makeFuseArgs(args);
        return new FuseArgs(args_l);
    }

    public FuseArgs(long ptr) {
        super(ptr, true);
    }
    public FuseArgs() {
        super();
    }

    public String toString() {
        return "lowlevel args ptr:" + super.getCPtr(this);
    }

    static {
        System.loadLibrary("jlowfuse");
    }

}
