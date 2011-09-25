package jlowfuse.async.tasks;

import fuse.FileInfo;
import jlowfuse.FuseReq;

public class Getxattr extends FilesystemOperation {
	protected long ino;
	protected String name;
	protected int size;
	
	public Getxattr(FuseReq req, long ino, String name, int size) {
		super(req);
		this.ino = ino;
		this.name = name;
		this.size = size;
	}
}
