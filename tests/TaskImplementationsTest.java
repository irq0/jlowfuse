import java.lang.reflect.Constructor;

import org.junit.*;

import static org.junit.Assert.*;

import jlowfuse.FuseReq;
import jlowfuse.async.tasks.*;
import jlowfuse.async.Context;
import jlowfuse.async.DefaultTaskImplementations;
import jlowfuse.async.TaskImplementations;

public class TaskImplementationsTest {
	TaskImplementations<Context> impls;
	Context context;
	
	@Before public void setUp() { 
		context = new Context();
		impls = new DefaultTaskImplementations<Context>();
	}
	
	@Test public void implsNotNull() {
		//awk 'BEGIN { OFS="" } /.*Impl =/ { split($4, res, "<"); class = res[1]; print "assertNotNull(impls.", tolower(class),"Impl);"  }' < DefaultTaskImplementations.java 
		assertNotNull(impls.initImpl);
		assertNotNull(impls.destroyImpl);
		assertNotNull(impls.lookupImpl);
		assertNotNull(impls.forgetImpl);
		assertNotNull(impls.getattrImpl);
		assertNotNull(impls.setattrImpl);
		assertNotNull(impls.readlinkImpl);
		assertNotNull(impls.mknodImpl);
		assertNotNull(impls.mkdirImpl);
		assertNotNull(impls.unlinkImpl);
		assertNotNull(impls.rmdirImpl);
		assertNotNull(impls.symlinkImpl);
		assertNotNull(impls.renameImpl);
		assertNotNull(impls.linkImpl);
		assertNotNull(impls.openImpl);
		assertNotNull(impls.readImpl);
		assertNotNull(impls.writeImpl);
		assertNotNull(impls.flushImpl);
		assertNotNull(impls.releaseImpl);
		assertNotNull(impls.fsyncImpl);
		assertNotNull(impls.opendirImpl);
		assertNotNull(impls.readdirImpl);
		assertNotNull(impls.releasedirImpl);
		assertNotNull(impls.fsyncdirImpl);
		assertNotNull(impls.statfsImpl);
		assertNotNull(impls.setxattrImpl);
		assertNotNull(impls.getxattrImpl);
		assertNotNull(impls.listxattrImpl);
		assertNotNull(impls.removexattrImpl);
		assertNotNull(impls.accessImpl);
		assertNotNull(impls.createImpl);
		assertNotNull(impls.bmapImpl);
	}
	
	@Test public void correctRuntimeTypes() {
		//awk 'BEGIN { OFS="" } /.*Impl =/ { split($4, res, "<"); class = res[1]; print "assertTrue(impls.", tolower(class),"Impl == ", class".class);"  }' < DefaultTaskImplementations.java 
		assertTrue(impls.initImpl == Init.class);
		assertTrue(impls.destroyImpl == Destroy.class);
		assertTrue(impls.lookupImpl == Lookup.class);
		assertTrue(impls.forgetImpl == Forget.class);
		assertTrue(impls.getattrImpl == Getattr.class);
		assertTrue(impls.setattrImpl == Setattr.class);
		assertTrue(impls.readlinkImpl == Readlink.class);
		assertTrue(impls.mknodImpl == Mknod.class);
		assertTrue(impls.mkdirImpl == Mkdir.class);
		assertTrue(impls.unlinkImpl == Unlink.class);
		assertTrue(impls.rmdirImpl == Rmdir.class);
		assertTrue(impls.symlinkImpl == Symlink.class);
		assertTrue(impls.renameImpl == Rename.class);
		assertTrue(impls.linkImpl == Link.class);
		assertTrue(impls.openImpl == Open.class);
		assertTrue(impls.readImpl == Read.class);
		assertTrue(impls.writeImpl == Write.class);
		assertTrue(impls.flushImpl == Flush.class);
		assertTrue(impls.releaseImpl == Release.class);
		assertTrue(impls.fsyncImpl == Fsync.class);
		assertTrue(impls.opendirImpl == Opendir.class);
		assertTrue(impls.readdirImpl == Readdir.class);
		assertTrue(impls.releasedirImpl == Releasedir.class);
		assertTrue(impls.fsyncdirImpl == Fsyncdir.class);
		assertTrue(impls.statfsImpl == Statfs.class);
		assertTrue(impls.setxattrImpl == Setxattr.class);
		assertTrue(impls.getxattrImpl == Getxattr.class);
		assertTrue(impls.listxattrImpl == Listxattr.class);
		assertTrue(impls.removexattrImpl == Removexattr.class);
		assertTrue(impls.accessImpl == Access.class);
		assertTrue(impls.createImpl == Create.class);
		assertTrue(impls.bmapImpl == Bmap.class);
	}

	// awk 'BEGIN { OFS="" } /.*Impl =/ { nam = substr($1, 0, index($1, "Impl") - 1); class = toupper(substr(nam,1,1)) substr(nam, 2); print "@Test public void getConstructorOf", class, "() {","\n", "    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.", nam, "Impl);","\n","    assertNotNull(constr);", "\n", "}"  ; }' < DefaultTaskImplementations.java
	@Test public void getConstructorOfInit() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.initImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfDestroy() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.destroyImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfLookup() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.lookupImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfForget() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.forgetImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfGetattr() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.getattrImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfSetattr() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.setattrImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfReadlink() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.readlinkImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfMknod() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.mknodImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfMkdir() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.mkdirImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfUnlink() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.unlinkImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfRmdir() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.rmdirImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfSymlink() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.symlinkImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfRename() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.renameImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfLink() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.linkImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfOpen() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.openImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfRead() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.readImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfWrite() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.writeImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfFlush() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.flushImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfRelease() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.releaseImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfFsync() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.fsyncImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfOpendir() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.opendirImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfReaddir() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.readdirImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfReleasedir() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.releasedirImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfFsyncdir() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.fsyncdirImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfStatfs() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.statfsImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfSetxattr() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.setxattrImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfGetxattr() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.getxattrImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfListxattr() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.listxattrImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfRemovexattr() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.removexattrImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfAccess() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.accessImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfCreate() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.createImpl);
	    assertNotNull(constr);
	}
	@Test public void getConstructorOfBmap() {
	    Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(impls.bmapImpl);
	    assertNotNull(constr);
	}
	
	/* FUSE is not connected so the reply functions do no good */
	@Test(expected=java.lang.UnsatisfiedLinkError.class)
	public void instantiateLookupTask() {
		Class<? extends JLowFuseTask<Context>> cls = impls.lookupImpl;		
    	Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(cls);
    	
    	JLowFuseTask<Context> task = TaskImplementations.instantiateTask(constr, new FuseReq(1337), 1, "");

		assertNotNull(task);
		task.run();
		// TODO add more tests when the exception handline from jext2 is ported ...
	}
}
