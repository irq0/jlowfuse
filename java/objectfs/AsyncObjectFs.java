package objectfs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import jlowfuse.JLowFuse;
import jlowfuse.JLowFuseArgs;
import jlowfuse.async.DefaultTaskImplementations;
import objectfs.async.tasks.Init;
import objectfs.async.ObjectFsContext;
import fuse.Fuse;
import fuse.SWIGTYPE_p_fuse_chan;
import fuse.SWIGTYPE_p_fuse_session;
import fuse.Session;

public class AsyncObjectFs {
	static {
		System.loadLibrary("jlowfuse");
	}

	public static void main(String[] strArgs) {
		String[] fuseArgs = { "-o foo,subtype=objfs", "-d" };
		String mountpoint = strArgs[0];
		JLowFuseArgs args = JLowFuseArgs.parseCommandline(fuseArgs);

		SWIGTYPE_p_fuse_chan chan = Fuse.mount(mountpoint, args);

		DefaultTaskImplementations impls = new DefaultTaskImplementations();
		impls.initImpl = Init.class;

		ExecutorService service = new ThreadPoolExecutor(10, 20, 5,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));

		ObjectFsContext context = new ObjectFsContext();
		
		SWIGTYPE_p_fuse_session sess = JLowFuse.asyncTasksNew(args, impls,
				service, context);

		Session.addChan(sess, chan);
		Session.loopSingle(sess);

		Session.removeChan(chan);

		Session.destroy(sess);
		Session.exit(sess);

		Fuse.unmount(mountpoint, chan);
	}

}
