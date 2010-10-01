package org.irq0.jlowfuse.reply;

public class Error extends Reply {
    private int err;
    
    public Error(int err) {
        this.err = err;
    }
}