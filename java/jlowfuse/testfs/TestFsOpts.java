package jlowfuse.testfs;

import fuse.*;
import jlowfuse.*;
import java.nio.ByteBuffer;
import java.math.BigInteger;

class TestFsOpts extends AbstractLowlevelOpts {
    public void init(FuseReq req, ByteBuffer data) {
        System.out.println("init: JAVA");
    }

    public void statfs(FuseReq req, long ino) {
        System.out.println("STATFS: java");
        
        statvfs stat = new statvfs();
        
        stat.setBsize(1024);
        stat.setFrsize(1024);
        stat.setBfree(new BigInteger("23"));
        stat.setBlocks(new BigInteger("42"));
        stat.setFiles(new BigInteger("23"));
        stat.setFavail(new BigInteger("5"));
        
        fuse.fuse_reply_statfs(req, stat);
         
        //        fuse.fuse_reply_err(req, Errno.ENOSYS);
    }
}

