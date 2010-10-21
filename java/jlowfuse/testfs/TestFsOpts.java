package jlowfuse.testfs;

import fuse.*;
import jlowfuse.*;
import java.nio.ByteBuffer;
import java.math.BigInteger;

class TestFsOps extends AbstractLowlevelOps {
    public void init() {
        System.out.println("init: JAVA");
    }

    public void statfs(FuseReq req, long ino) {
        System.out.println("STATFS:"  + req + " ino: " + ino);
        
        statvfs stat = new statvfs();
        
        stat.setBsize(1024);
        stat.setFrsize(1024);
        stat.setBfree(new BigInteger("19"));
        stat.setBlocks(new BigInteger("42"));
        stat.setFiles(new BigInteger("23"));
        stat.setFavail(new BigInteger("5"));
        
        fuse.fuse_reply_statfs(req, stat);
         
        //        fuse.fuse_reply_err(req, fuseConstants.ENOSYS);
    }
}

