package objectfs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import jlowfuse.JLowFuse;
import jlowfuse.JLowFuseArgs;
import jlowfuse.async.DefaultTaskImplementations;
import jlowfuse.async.TaskImplementations;
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

		ObjectFsContext context = new ObjectFsContext();
		DefaultTaskImplementations<ObjectFsContext> impls = 
			new DefaultTaskImplementations<ObjectFsContext>();

		impls.getattrImpl = TaskImplementations.getImpl("objectfs.async.tasks.Getattr");
		impls.initImpl = TaskImplementations.getImpl("objectfs.async.tasks.Init");
		impls.lookupImpl = TaskImplementations.getImpl("objectfs.async.tasks.Lookup");
		impls.mkdirImpl = TaskImplementations.getImpl("objectfs.async.tasks.Mkdir");
		impls.mknodImpl = TaskImplementations.getImpl("objectfs.async.tasks.Mknod");
		impls.readImpl = TaskImplementations.getImpl("objectfs.async.tasks.Read");
		impls.readdirImpl = TaskImplementations.getImpl("objectfs.async.tasks.Readdir");
		impls.rmdirImpl = TaskImplementations.getImpl("objectfs.async.tasks.Rmdir");
		impls.setattrImpl = TaskImplementations.getImpl("objectfs.async.tasks.Setattr");
		impls.statfsImpl = TaskImplementations.getImpl("objectfs.async.tasks.Statfs");
		impls.unlinkImpl = TaskImplementations.getImpl("objectfs.async.tasks.Unlink");
		impls.writeImpl = TaskImplementations.getImpl("objectfs.async.tasks.Write");
		
		ExecutorService service = new ThreadPoolExecutor(10, 20, 5,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));

		
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
