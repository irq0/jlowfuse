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

import fuse.Errno;

import jlowfuse.FuseReq;
import jlowfuse.Reply;

public class JLowFuseTask implements RunnableFuture<Object> {
	protected FuseReq req;

	public JLowFuseTask(FuseReq req) {
		this.req = req;
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
        Reply.err(req, Errno.ENOSYS);
    }	
}
