package objectfs.async.tasks;

import jlowfuse.async.tasks.JLowFuseTask;

public class Init extends JLowFuseTask implements jlowfuse.async.tasks.Init {

	@Override
	public void attributes() {
		// TODO Auto-generated method stub

	}

    public void run() {
	    super.run();
	    System.out.println("Hello World!");
    }
}