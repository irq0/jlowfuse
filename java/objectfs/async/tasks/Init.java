package objectfs.async.tasks;

import objectfs.ObjectFS;
import objectfs.async.ObjectFsContext;

public class Init extends jlowfuse.async.tasks.Init<ObjectFsContext> {

    public void run() {
	    super.run();
	    
	    ObjectFS fs = new ObjectFS();
	    context.fs = fs;
    }

}