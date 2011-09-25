package jlowfuse.async.tasks;

import java.nio.ByteBuffer;

import fuse.FileInfo;
import jlowfuse.FuseReq;

public class Write extends FilesystemOperation {
	protected long ino;
	protected ByteBuffer buf;
	protected long off;
	protected FileInfo fi;
	
	public Write(FuseReq req, long ino, ByteBuffer buf, long off, FileInfo fi) {
		super(req);
		this.ino = ino;
		this.buf = buf;
		this.off = off;
		this.fi = fi;
	}
}
