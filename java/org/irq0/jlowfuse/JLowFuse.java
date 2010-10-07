package org.irq0.jlowfuse;

public class JLowFuse {
        public native int init(Object opts);

        static {
                System.loadLibrary("jlowfuse");
        }
} 