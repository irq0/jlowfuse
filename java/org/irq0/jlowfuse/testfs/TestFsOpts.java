package org.irq0.jlowfuse.testfs;

import org.irq0.jlowfuse.*;
import org.irq0.jlowfuse.reply.*;
import java.nio.ByteBuffer;

class TestFsOpts extends AbstractLowlevelOpts {
    public void init(ByteBuffer data) {
        System.out.println("init: JAVA");
    }

    public Reply statfs(long ino) {
        //        return new FsError(Errno.ENOSYS);
        
        Statvfs stat = new Statvfs();
        stat.setBlocksAvail(23);

        return stat;
        
    }
}
