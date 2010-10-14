package jlowfuse;

import java.nio.CharBuffer;
import java.nio.ByteBuffer;

import fuse.*;

public class FuseArgs extends fuse_args {
    /*    public FuseArgs(String[] args) {
        addArgs(args);
    }

    public void addArg(String arg) {
        fuse.fuse_opt_add_arg(this, arg);
    }
    
    public void addArgs(String[] args) {
        for (String s: args) {
            addArg(s);
        }
    }
    */
}
