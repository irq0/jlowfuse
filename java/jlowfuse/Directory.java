package jlowfuse;

import fuse.*;

public class Directory extends SWIGTYPE_p_void {
    private long dirbuf_p;
    
    protected static native long dirbufNew();
    protected static native long dirbufDelete(long dirbuf);
    protected static native void dirbufAdd(long req, long dirbuf,
                                           String name, long ino);

    public Directory() {
        super(Directory.dirbufNew(), false);
        this.dirbuf_p = getCPtr(this);
    }

    public synchronized void finalize() {
        dirbufDelete(this.dirbuf_p);
    }

    public void add(FuseReq req, long ino, String name) {
        dirbufAdd(req.getCPtr(), this.dirbuf_p, name, ino);
    }
}
