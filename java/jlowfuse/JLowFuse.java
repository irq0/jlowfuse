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

import java.util.concurrent.ExecutorService;

import jlowfuse.async.AsyncLowlevelOps;
import jlowfuse.async.Context;
import jlowfuse.async.TaskImplementations;
import jlowfuse.classic.FuseLowlevelOps;
import fuse.Fuse;
import fuse.FuseArgs;
import fuse.SWIGTYPE_p_fuse_session;

public class JLowFuse {
	public native int init(Object opts);

	private static native long setOps(OpsProxy ops, ThreadGroup tgroup);
	private static native void setBufferManager(BufferManager man, boolean forRead, boolean forWrite);

	public static void useBufferManager(BufferManager man, boolean forRead, boolean forWrite) {
		setBufferManager(man, forRead, forWrite);
	}

	/**
	 * Establish new lowlevel session. Registers `LowlevelOps` in fuse c api and
	 * calles fuse_lowlevel_new
	 *
	 * @param args
	 *            Fuse Args eg. Commandline
	 * @param ops
	 *            LowlevelOps instance - implements filesystem operations
	 * @param tgroup
	 *            java threads created by JNI AttachCurrentThreadAsDaemon
	 * @return Opaque pointer to fuse_session type
	 */
	public static SWIGTYPE_p_fuse_session lowlevelNew(FuseArgs args, LowlevelOps ops, ThreadGroup tgroup) {
		LowlevelOpsProxy proxy = new LowlevelOpsProxy();
		proxy.register(ops);

		long ops_p = JLowFuse.setOps(proxy, tgroup);
		FuseLowlevelOps ops_f = new FuseLowlevelOps(ops_p);

		return Fuse.lowlevelNew(args, ops_f, 144, null); /*
														 * 144 = sizeof(fuse
														 * ops)
														 */
	}

	/**
	 * Establish new lowlevel session. Registers `LowlevelOps` in fuse c api and
	 * calles fuse_lowlevel_new
	 *
	 * @param args
	 *            Fuse Args eg. Commandline
	 * @param ops
	 *            LowlevelOps instance - implements filesystem operations
	 * @return Opaque pointer to fuse_session type
	 */
	public static SWIGTYPE_p_fuse_session lowlevelNew(FuseArgs args, LowlevelOps ops) {
		return lowlevelNew(args, ops, null);
	}

	/**
	 * Create new lowlevel session using the concurrency api.
	 *
	 * @param args
	 *            Fuse Args eg. Commandline
	 * @param executor
	 *            handles Tasks
	 * @param taskImplementations
	 *            class with all the implementations for the operations
	 * @return Opaque pointer to fuse_session type
	 */
	public static <CTX extends Context> SWIGTYPE_p_fuse_session asyncTasksNew(FuseArgs args, TaskImplementations<CTX> taskImplementations, ExecutorService service, CTX context) {

		LowlevelOpsProxy proxy = new LowlevelOpsProxy();
		AsyncLowlevelOps<CTX> ops = new AsyncLowlevelOps<CTX>(taskImplementations, service, context);

		proxy.register(ops);

		long ops_p = JLowFuse.setOps(proxy, null);
		FuseLowlevelOps ops_f = new FuseLowlevelOps(ops_p);

		return Fuse.lowlevelNew(args, ops_f, 144, null);
	}

	static {
		System.loadLibrary("jlowfuse");
	}
}
