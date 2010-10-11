package jlowfuse.testfs;

import jlowfuse.*;
import java.nio.ByteBuffer;

class TestFsOpts extends AbstractLowlevelOpts {
    public void init(ByteBuffer data) {
        System.out.println("init: JAVA");
    }

    public void statfs(long ino) {
    }
}
