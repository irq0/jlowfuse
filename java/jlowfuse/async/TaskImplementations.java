/*
 * Copyright (c) 2011 Marcel Lauhoff.
 * 
 * This file is part of jlowfuse.
 * 
 * jlowfuse is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * jlowfuse is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with jlowfuse.  If not, see <http://www.gnu.org/licenses/>.
 */

package jlowfuse.async;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import jlowfuse.async.tasks.*;

public class TaskImplementations<CTX extends Context> {
	public Class<? extends Init<CTX>> initImpl = null;
	public Class<? extends Destroy<CTX>> destroyImpl = null;
	public Class<? extends Lookup<CTX>> lookupImpl = null;
	public Class<? extends Forget<CTX>> forgetImpl = null;
	public Class<? extends Getattr<CTX>> getattrImpl = null;
	public Class<? extends Setattr<CTX>> setattrImpl = null;
	public Class<? extends Readlink<CTX>> readlinkImpl = null;
	public Class<? extends Mknod<CTX>> mknodImpl = null;
	public Class<? extends Mkdir<CTX>> mkdirImpl = null;
	public Class<? extends Unlink<CTX>> unlinkImpl = null;
	public Class<? extends Rmdir<CTX>> rmdirImpl = null;
	public Class<? extends Symlink<CTX>> symlinkImpl = null;
	public Class<? extends Rename<CTX>> renameImpl = null;
	public Class<? extends Link<CTX>> linkImpl = null;
	public Class<? extends Open<CTX>> openImpl = null;
	public Class<? extends Read<CTX>> readImpl = null;
	public Class<? extends Write<CTX>> writeImpl = null;
	public Class<? extends Flush<CTX>> flushImpl = null;
	public Class<? extends Release<CTX>> releaseImpl = null;
	public Class<? extends Fsync<CTX>> fsyncImpl = null;
	public Class<? extends Opendir<CTX>> opendirImpl = null;
	public Class<? extends Readdir<CTX>> readdirImpl = null;
	public Class<? extends Releasedir<CTX>> releasedirImpl = null;
	public Class<? extends Fsyncdir<CTX>> fsyncdirImpl = null;
	public Class<? extends Statfs<CTX>> statfsImpl = null;
	public Class<? extends Setxattr<CTX>> setxattrImpl = null;
	public Class<? extends Getxattr<CTX>> getxattrImpl = null;
	public Class<? extends Listxattr<CTX>> listxattrImpl = null;
	public Class<? extends Removexattr<CTX>> removexattrImpl = null;
	public Class<? extends Access<CTX>> accessImpl = null;
	public Class<? extends Create<CTX>> createImpl = null;
	public Class<? extends Bmap<CTX>> bmapImpl = null;
	
	@SuppressWarnings("unchecked")
	public static <T extends JLowFuseTask<? extends Context>> Class<T> getImpl(String classname) {	
		try {
			Class<T> result = (Class<T>) Class.forName(classname);			
			return result;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Should not happen");
		}				
	}
	
	/** Get constructor for the given task implementation */
	@SuppressWarnings("unchecked")
	public static <CTX extends Context> Constructor<? extends JLowFuseTask<CTX>> getTaskConstructor(Class<? extends JLowFuseTask<CTX>> impl) {
		Constructor<? extends JLowFuseTask<CTX>>[] c = 
			(Constructor<? extends JLowFuseTask<CTX>>[]) impl.getConstructors();
		
		if (c.length > 1)
			throw new RuntimeException(String.format("Tasks %s may only have one constructur", impl.getName()));
		else if (c.length == 0)
			throw new RuntimeException(String.format("Task %s has no constructor ?!", impl.getName()));
		
		return c[0];
	}
	
	/** Create instance of task object */ 
	public static <CTX extends Context> JLowFuseTask<CTX> instantiateTask(Constructor<? extends JLowFuseTask<CTX>> constructor, Object ... arguments) {
		try {
	        JLowFuseTask<CTX> task = (JLowFuseTask<CTX>)(constructor.newInstance(arguments));
	        return task;
	        
	    } catch (IllegalArgumentException e) { /* should only happen if this class is implemented wrongly */
	    	e.printStackTrace();
	    	throw new RuntimeException(String.format("Incorrect constructor call for class %s", constructor.getName()));
	    } catch (InstantiationException e) {
	    	e.printStackTrace();
	    	throw new RuntimeException("Should not happen " + e.getMessage());
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Should not happen " + e.getMessage());
	    } catch (InvocationTargetException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Should not happen " + e.getMessage());
	    }
	}
}
