package org.irq0.jlowfuse.testfs;

import org.irq0.jlowfuse.*;
import org.irq0.jlowfuse.reply.*;
import java.nio.ByteBuffer;

class TestFsOpts extends AbstractLowlevelOpts {
    Reply init(ByteBuffer data) {
        System.out.println("juhu  -   java");
        return new org.irq0.jlowfuse.reply.Error(23);
    }
}
