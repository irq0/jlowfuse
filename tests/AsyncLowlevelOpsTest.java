import java.util.concurrent.ExecutorService;

import org.junit.*;

import static org.junit.Assert.*;

import jlowfuse.async.AsyncLowlevelOps;
import jlowfuse.async.Context;
import jlowfuse.async.DefaultTaskImplementations;
import jlowfuse.async.TaskImplementations;

public class AsyncLowlevelOpsTest {
	ExecutorService executor;
	TaskImplementations<Context> impls;
	Context context;
	AsyncLowlevelOps<Context> ops;
	
	@Before public void setUp() { 
		context = new Context();
		impls = new DefaultTaskImplementations<Context>();
		executor = null;
		ops = new AsyncLowlevelOps<Context>(impls, executor, context);
	}
	
	@Test public void noop() {
		assertTrue("Placeholder test", true);
	}
		
}
