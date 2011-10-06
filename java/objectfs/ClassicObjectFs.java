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
