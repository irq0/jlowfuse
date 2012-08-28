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

package objectfs;

import jlowfuse.JLowFuse;
import jlowfuse.JLowFuseArgs;
import objectfs.classic.ObjectFsOps;
import fuse.Fuse;
import fuse.SWIGTYPE_p_fuse_chan;
import fuse.SWIGTYPE_p_fuse_session;
import fuse.Session;

public class ClassicObjectFs {
	static {
		System.loadLibrary("jlowfuse");
	}

	public static void main(String[] strArgs) {
		String[] fuseArgs = { "-o foo,subtype=objfs", "-d" };
		String mountpoint = strArgs[0];
		JLowFuseArgs args = JLowFuseArgs.parseCommandline(fuseArgs);

		SWIGTYPE_p_fuse_chan chan = Fuse.mount(mountpoint, args);
		SWIGTYPE_p_fuse_session sess = JLowFuse.lowlevelNew(args,
				new ObjectFsOps());

		Session.addChan(sess, chan);
		Session.loopSingle(sess);
		// Session.loopMulti(sess);

		Session.removeChan(chan);

		Session.destroy(sess);
		Session.exit(sess);

		Fuse.unmount(mountpoint, chan);
	}

}
