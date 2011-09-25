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

import jlowfuse.FuseReq;

public class JLowFuseTask implements RunnableFuture<Object> {
	protected FuseReq req;

	public JLowFuseTask(FuseReq req) {
		this.req = req;
	}
	
	public JLowFuseTask() {
		
	}

	@Override
    public boolean cancel(boolean arg0) {
	    // TODO Auto-generated method stub
	    return false;
    }

	@Override
    public Object get() throws InterruptedException, ExecutionException {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Object get(long arg0, TimeUnit arg1) throws InterruptedException,
            ExecutionException, TimeoutException {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public boolean isCancelled() {
	    // TODO Auto-generated method stub
	    return false;
    }

	@Override
    public boolean isDone() {
	    // TODO Auto-generated method stub
	    return false;
    }

	@Override
    public void run() {
	    // TODO Auto-generated method stub
	    
    }
	
}
