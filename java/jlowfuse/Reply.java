package jlowfuse;

import java.nio.ByteBuffer;

public class Reply {
    private static native int jniReplyByteBuffer(long req, ByteBuffer buf,
                                                 long off, long maxsize);

    public static int replyByteBuffer(FuseReq req, ByteBuffer buf,
                                      long off, long maxsize) {
        return jniReplyByteBuffer(req.getCPtr(), buf, off, maxsize);
    }
}   
