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

import fuse.FuseArgs;

public class JLowFuseArgs extends FuseArgs {
	private static native long makeFuseArgs(String[] args);

	public static JLowFuseArgs parseCommandline(String[] args) {
		long args_l = JLowFuseArgs.makeFuseArgs(args);
		return new JLowFuseArgs(args_l);
	}

	public JLowFuseArgs(long ptr) {
		super(ptr, true);
	}

	public JLowFuseArgs() {
		super();
	}

	@Override
	public String toString() {
		return "lowlevel args ptr:" + super.getCPtr(this);
	}

	static {
		System.loadLibrary("jlowfuse");
	}

}
