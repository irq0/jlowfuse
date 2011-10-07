import java.lang.reflect.Constructor;

import org.junit.*;

import static org.junit.Assert.*;

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
	
	@Test public void getTaskConstructorOfInitImpl() {
		Class<? extends JLowFuseTask<Context>> c = impls.initImpl;
		
    	Constructor<? extends JLowFuseTask<Context>> constr = TaskImplementations.getTaskConstructor(c);
    	
    	assertNotNull(constr);
	}
}
