/**
 * Data structure class to hold the mapping to 
 * the implemantations of jlowfuse async tasks
 */

package jlowfuse.async;

import jlowfuse.async.tasks.*;

public class TaskImplementations {
	Class<JLowFuseTask> initImpl;
	Class<JLowFuseTask> destroyImpl;
	Class<JLowFuseTask> lookupImpl;
	Class<JLowFuseTask> forgetImpl;
	Class<JLowFuseTask> getattrImpl;
	Class<JLowFuseTask> setattrImpl;
	Class<JLowFuseTask> readlinkImpl;
	Class<JLowFuseTask> mknodImpl;
	Class<JLowFuseTask> mkdirImpl;
	Class<JLowFuseTask> unlinkImpl;
	Class<JLowFuseTask> rmdirImpl;
	Class<JLowFuseTask> symlinkImpl;
	Class<JLowFuseTask> renameImpl;
	Class<JLowFuseTask> linkImpl;
	Class<JLowFuseTask> openImpl;
	Class<JLowFuseTask> readImpl;
	Class<JLowFuseTask> writeImpl;
	Class<JLowFuseTask> flushImpl;
	Class<JLowFuseTask> releaseImpl;
	Class<JLowFuseTask> fsyncImpl;
	Class<JLowFuseTask> opendirImpl;
	Class<JLowFuseTask> readdirImpl;
	Class<JLowFuseTask> releasedirImpl;
	Class<JLowFuseTask> fsyncdirImpl;
	Class<JLowFuseTask> statfsImpl;
	Class<JLowFuseTask> setxattrImpl;
	Class<JLowFuseTask> getxattrImpl;
	Class<JLowFuseTask> listxattrImpl;
	Class<JLowFuseTask> removexattrImpl;
	Class<JLowFuseTask> accessImpl;
	Class<JLowFuseTask> createImpl;
	Class<JLowFuseTask> bmapImpl;

	private boolean taskImplsInterface(Class impl,  Class intf) {
		for (Class i : impl.getInterfaces()) {
			if (intf.equals(i))
				return true;
		}
		return false;
	}

	private void throwExceptionIfNotImplsInterface(Class impl, Class intf) {
		if (! taskImplsInterface(impl, intf)) {
			StringBuilder s = new StringBuilder();
			s.append("Class >>");
			s.append(impl.getName());
			s.append("<<");
			s.append(" does not implement interface ");
			s.append(intf.getName());

			throw new IllegalArgumentException(s.toString());
		}
	}

	private Class taskIntfByName(String name) {
		try {
			Class result = Class.forName("jlowfuse.async.tasks." + name);
			return result;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e); /* should not happen */
		}
	}
	
	
	public void setInit(Class<JLowFuseTask> impl) {
		throwExceptionIfNotImplsInterface(impl, taskIntfByName("Init"));
		this.initImpl = impl;
	}
	
	public JLowFuseTask createInstanceOfInit() {
		try {
	        return initImpl.newInstance();
        } catch (InstantiationException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        } catch (IllegalAccessException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		return null;
	}
}



