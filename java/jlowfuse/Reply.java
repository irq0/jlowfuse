/*
 * Copyright (c) 2011 Marcel Lauhoff.
 * 
 * This file is part of jlowfuse.
 * 
 * jlowfuse is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * jlowfuse is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with jlowfuse.  If not, see <http://www.gnu.org/licenses/>.
 */

package jlowfuse;

import java.nio.ByteBuffer;

import fuse.Dirbuf;
import fuse.FuseExtra;
import fuse.FuseReply;

public class Reply extends FuseReply {
	private static native int jniReplyByteBuffer(long req, ByteBuffer buf, long off, long maxsize);

	public static int byteBuffer(FuseReq req, ByteBuffer buf, long off, long maxsize) {
		return jniReplyByteBuffer(req.getCPtr(), buf, off, maxsize);
	}

	public static int dirBufLimited(FuseReq req, Dirbuf dir, long off, long maxsize) {
		return FuseExtra.replyBufLimited(req, dir.getP(), dir.getSize(), off, maxsize);
	}

}
