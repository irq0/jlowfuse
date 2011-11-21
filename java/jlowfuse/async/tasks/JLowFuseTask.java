/**
 * Base class for jlowfuse Tasks.
 *
 * @author Marcel Lauhoff <ml@irq0.org>
 *
 */

package jlowfuse.async.tasks;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import jlowfuse.async.Context;

public class JLowFuseTask<CTX extends Context> implements RunnableFuture<Object> {
	private static AtomicInteger idCounter = new AtomicInteger(0);
	
	private final int id = idCounter.incrementAndGet();
	
	public final int getId() {
		return id;
	}
	
	protected CTX context;
	
	public void initContext(CTX context) {
		this.context = context;
	}
	
	public JLowFuseTask() {
	}
	
    public boolean cancel(boolean arg0) {
	    return false;
    }

    public Object get() throws InterruptedException, ExecutionException {
	    return null;
    }

    public Object get(long arg0, TimeUnit arg1) throws InterruptedException,
            ExecutionException, TimeoutException {
	    return null;
    }

    public boolean isCancelled() {
	    return false;
    }

    public boolean isDone() {
	    return false;
    }

    public void run() {
    }
    
	public String toString() {
		return new StringBuilder()
			.append(this.getClass().getSimpleName())
			.append("#")
			.append(this.id)
			.toString();
	}
}
