/**
 * Data structure class to hold the mapping to 
 * the implemantations of jlowfuse async tasks
 */

package jlowfuse.async;

import jlowfuse.async.tasks.*;

public class TaskImplementations {
	Class<Init> initImpl;
	Class destroyImpl;
	Class lookupImpl;
	Class forgetImpl;
	Class getattrImpl;
	Class setattrImpl;
	Class readlinkImpl;
	Class mknodImpl;
	Class mkdirImpl;
	Class unlinkImpl;
	Class rmdirImpl;
	Class symlinkImpl;
	Class renameImpl;
	Class linkImpl;
	Class openImpl;
	Class readImpl;
	Class writeImpl;
	Class flushImpl;
	Class releaseImpl;
	Class fsyncImpl;
	Class opendirImpl;
	Class readdirImpl;
	Class releasedirImpl;
	Class fsyncdirImpl;
	Class statfsImpl;
	Class setxattrImpl;
	Class getxattrImpl;
	Class listxattrImpl;
	Class removexattrImpl;
	Class accessImpl;
	Class createImpl;
	Class bmapImpl;

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
	
	
	public void setInit(Class impl) {
		throwExceptionIfNotImplsInterface(impl, taskIntfByName("Init"));
		this.initImpl = impl;
	}
}



