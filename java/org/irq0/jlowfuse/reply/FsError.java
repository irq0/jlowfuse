package org.irq0.jlowfuse.reply;

public class FsError extends Reply {
    private int err;

    public int getErr() { return this.err; }
    public FsError(int err) {
        this.err = err;
    }
}