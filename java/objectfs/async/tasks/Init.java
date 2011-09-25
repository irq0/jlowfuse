package objectfs.async.tasks;

public class Init extends jlowfuse.async.tasks.Init {

    public void run() {
	    super.run();
	    System.out.println("Hello World!");
    }

}