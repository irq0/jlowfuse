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

package jlowfuse;

import java.nio.ByteBuffer;

public interface OpsProxy {
	/**
	 * Initialize filesystem Called before any other filesystem method
	 * 
	 * @param data
	 *            cpointer to user date passed to fuse_lowlevel_new
	 * @param conn
	 *            cpointer to fuse_conn_info struct
	 */
	public void init(long data, long conn);

	/**
	 * Clean up filesystem Called on filesystem exit
	 * 
	 * @param data
	 *            cpointer ot user data passed to fuse_lowlevel_new
	 */
	public void destroy(long data);

	/**
	 * Look up a directory entry by name and get its attributes.
	 * 
	 * @param req
	 *            request handle
	 * @param parent
	 *            inode number of the parent directory
	 * @param name
	 *            the name to look up
	 */
	public void lookup(long req, long parent, String name);

	/**
	 * Forget about an inode The nlookup parameter indicates the number of
	 * lookups previously performed on this inode.
	 * 
	 * If the filesystem implements inode lifetimes, it is recommended that
	 * inodes acquire a single reference on each lookup, and lose nlookup
	 * references on each forget.
	 * 
	 * The filesystem may ignore forget calls, if the inodes don't need to have
	 * a limited lifetime.
	 * 
	 * On unmount it is not guaranteed, that all referenced inodes will receive
	 * a forget message.
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            inode number
	 * @param nlookup
	 *            number of lookups to forget
	 */
	public void forget(long req, long ino, long nlookup);

	/**
	 * Get file attributes
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            inode number
	 * @param fi
	 *            file information or c-nullpointer
	 */
	public void getattr(long req, long ino, long fi);

	/**
	 * Set file attributes
	 * 
	 * In the 'attr' argument only members indicated by the 'to_set' bitmask
	 * contain valid values. Other members contain undefined values
	 * 
	 * If the setattr was invoked from the ftruncate() system call under Linux
	 * kernel versions 2.6.15 or later, the fi->fh will contain the value set by
	 * the open method or will be undefined if the open method didn't set any
	 * value. Otherwise (not ftruncate call, or kernel version earlier than
	 * 2.6.15) the fi parameter will be NULL.
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            inode number
	 * @param attr
	 *            attributes
	 * @param to_set
	 *            bit mask of attributes which should be set
	 * @param fi
	 *            file information or c-nullpointer
	 */
	public void setattr(long req, long ino, long attr, int to_set, long fi);

	/**
	 * Read symbolic link
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            inode number
	 */
	public void readlink(long req, long ino);

	/**
	 * Create file node
	 * 
	 * Create a regular file, character device, block device, fifo or socket
	 * node.
	 * 
	 * @param req
	 *            request handle
	 * @param parent
	 *            inode number of the parent directory
	 * @param name
	 *            name of the new node
	 * @param mode
	 *            file type and mode with which to create the new file
	 * @param rdev
	 *            device number (only when create device)
	 */
	public void mknod(long req, long parent, String name, short mode, short rdev);

	/**
	 * Create a directory
	 * 
	 * @param req
	 *            request handle
	 * @param parent
	 *            inode number of the parent directory
	 * @param name
	 *            name of the new node
	 * @param mode
	 *            file type and mode with which to create the new file
	 */
	public void mkdir(long req, long parent, String name, short mode);

	/**
	 * Remove a file
	 * 
	 * @param req
	 *            request handle
	 * @param parent
	 *            inode number of the parent directory
	 * @param name
	 *            name of the inode to remove
	 */
	public void unlink(long req, long parent, String name);

	/**
	 * Remove a directory
	 * 
	 * @param req
	 *            request handle
	 * @param parent
	 *            inode number of the parent directory
	 * @param name
	 *            name of the inode to remove
	 */
	public void rmdir(long req, long parent, String name);

	/**
	 * Create a symbolic link
	 * 
	 * @param req
	 *            request handle
	 * @param link
	 *            the contents of the symbolic link
	 * @param parent
	 *            inode number of the parent directory
	 * @param name
	 *            name of the inode to create
	 */
	public void symlink(long req, String link, long parent, String name);

	/**
	 * Rename a file
	 * 
	 * @param req
	 *            request handle
	 * @param parent
	 *            inode number of the old parent directory
	 * @param name
	 *            old name
	 * @param newparent
	 *            inode number of the new parent directory
	 * @param newname
	 *            new name
	 */
	public void rename(long req, long parent, String name, long newparent,
			String newname);

	/**
	 * Create a hard link
	 * 
	 * @param req
	 *            request handle
	 * @param parent
	 *            the old inode number
	 * @param newparent
	 *            inode number of the new parent directory
	 * @param newname
	 *            new name to create
	 */
	public void link(long req, long ino, long newparent, String newname);

	/**
	 * Open a file Open flags (with the exception of O_CREAT, O_EXCL, O_NOCTTY
	 * and O_TRUNC) are available in fi->flags.
	 * 
	 * Filesystem may store an arbitrary file handle (pointer, index, etc) in
	 * fi->fh, and use this in other all other file operations (read, write,
	 * flush, release, fsync).
	 * 
	 * Filesystem may also implement stateless file I/O and not store anything
	 * in fi->fh.
	 * 
	 * There are also some flags (direct_io, keep_cache) which the filesystem
	 * may set in fi, to change the way the file is opened. See fuse_file_info
	 * structure in <fuse_common.h> for more details
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            inode number
	 * @param fi
	 *            file information or c-nullpointer
	 */
	public void open(long req, long ino, long fi);

	/**
	 * Read data
	 * 
	 * Read should send exactly the number of bytes requested except on EOF or
	 * error, otherwise the rest of the data will be substituted with zeroes. An
	 * exception to this is when the file has been opened in 'direct_io' mode,
	 * in which case the return value of the read system call will reflect the
	 * return value of this operation.
	 * 
	 * fi->fh will contain the value set by the open method, or will be
	 * undefined if the open method didn't set any value.
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            inode number
	 * @param size
	 *            number of bytes to read
	 * @param off
	 *            offset to read from
	 * @param fi
	 *            file information or c-nullpointer
	 */
	public void read(long req, long ino, long size, long off, long fi);

	/**
	 * Write data
	 * 
	 * Write should return exactly the number of bytes requested except on
	 * error. An exception to this is when the file has been opened in
	 * 'direct_io' mode, in which case the return value of the write system call
	 * will reflect the return value of this operation.
	 * 
	 * fi->fh will contain the value set by the open method, or will be
	 * undefined if the open method didn't set any value.
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            the inode number
	 * @param buf
	 *            data to write
	 * @param size
	 *            number of bytes to write
	 * @param off
	 *            offset to write to
	 * @param fi
	 *            file information
	 */
	public void write(long req, long ino, ByteBuffer buf, long size, long off,
			long fi);

	/**
	 * Flush method
	 * 
	 * This is called on each close() of the opened file.
	 * 
	 * Since file descriptors can be duplicated (dup, dup2, fork), for one open
	 * call there may be many flush calls.
	 * 
	 * Filesystems shouldn't assume that flush will always be called after some
	 * writes, or that if will be called at all.
	 * 
	 * fi->fh will contain the value set by the open method, or will be
	 * undefined if the open method didn't set any value.
	 * 
	 * NOTE: the name of the method is misleading, since (unlike fsync) the
	 * filesystem is not forced to flush pending writes. One reason to flush
	 * data, is if the filesystem wants to return write errors.
	 * 
	 * If the filesystem supports file locking operations (setlk, getlk) it
	 * should remove all locks belonging to 'fi->owner'.
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            the inode number
	 * @param fi
	 *            file information
	 */
	public void flush(long req, long ino, long fi);

	/**
	 * Release an open file
	 * 
	 * Release is called when there are no more references to an open file: all
	 * file descriptors are closed and all memory mappings are unmapped.
	 * 
	 * For every open call there will be exactly one release call.
	 * 
	 * The filesystem may reply with an error, but error values are not returned
	 * to close() or munmap() which triggered the release.
	 * 
	 * fi->fh will contain the value set by the open method, or will be
	 * undefined if the open method didn't set any value. fi->flags will contain
	 * the same flags as for open.
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            the inode number
	 * @param fi
	 *            file information
	 */
	public void release(long req, long ino, long fi);

	/**
	 * Synchronize file contents
	 * 
	 * If the datasync parameter is non-zero, then only the user data should be
	 * flushed, not the meta data.
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            the inode number
	 * @param datasync
	 *            flag indicating if only data should be flushed
	 * @param fi
	 *            file information
	 */
	public void fsync(long req, long ino, int datasync, long fi);

	/**
	 * Open a directory
	 * 
	 * Filesystem may store an arbitrary file handle (pointer, index, etc) in
	 * fi->fh, and use this in other all other directory stream operations
	 * (readdir, releasedir, fsyncdir).
	 * 
	 * Filesystem may also implement stateless directory I/O and not store
	 * anything in fi->fh, though that makes it impossible to implement standard
	 * conforming directory stream operations in case the contents of the
	 * directory can change between opendir and releasedir.
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            the inode number
	 * @param fi
	 *            file information
	 */
	public void opendir(long req, long ino, long fi);

	/**
	 * Read directory
	 * 
	 * Send a buffer filled using fuse_add_direntry(), with size not exceeding
	 * the requested size. Send an empty buffer on end of stream.
	 * 
	 * fi->fh will contain the value set by the opendir method, or will be
	 * undefined if the opendir method didn't set any value.
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            the inode number
	 * @param size
	 *            maximum number of bytes to send
	 * @param off
	 *            offset to continue reading the directory stream
	 * @param fi
	 *            file information
	 */
	public void readdir(long req, long ino, long size, long off, long fi);

	/**
	 * Release an open directory
	 * 
	 * For every opendir call there will be exactly one releasedir call.
	 * 
	 * fi->fh will contain the value set by the opendir method, or will be
	 * undefined if the opendir method didn't set any value.
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            the inode number
	 * @param fi
	 *            file information
	 */
	public void releasedir(long req, long ino, long fi);

	/**
	 * Synchronize directory contents
	 * 
	 * If the datasync parameter is non-zero, then only the directory contents
	 * should be flushed, not the meta data.
	 * 
	 * fi->fh will contain the value set by the opendir method, or will be
	 * undefined if the opendir method didn't set any value.
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            the inode number
	 * @param datasync
	 *            flag indicating if only data should be flushed
	 * @param fi
	 *            file information
	 */
	public void fsyncdir(long req, long ino, int datasync, long fi);

	/**
	 * Get file system statistics
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            the inode number, zero means "undefined"
	 */
	public void statfs(long req, long ino);

	/**
	 * Set an extended attribute
	 */
	public void setxattr(long req, long ino, String name, ByteBuffer value,
			int size, int flags);

	/**
	 * Get an extended attribute
	 * 
	 * If size is zero, the size of the value should be sent with
	 * fuse_reply_xattr.
	 * 
	 * If the size is non-zero, and the value fits in the buffer, the value
	 * should be sent with fuse_reply_buf.
	 * 
	 * If the size is too small for the value, the ERANGE error should be sent.
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            the inode number
	 * @param name
	 *            of the extended attribute
	 * @param size
	 *            maximum size of the value to send
	 */
	public void getxattr(long req, long ino, String name, int size);

	/**
	 * List extended attribute names
	 * 
	 * If size is zero, the total size of the attribute list should be sent with
	 * fuse_reply_xattr.
	 * 
	 * If the size is non-zero, and the null character separated attribute list
	 * fits in the buffer, the list should be sent with fuse_reply_buf.
	 * 
	 * If the size is too small for the list, the ERANGE error should be sent.
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            the inode number
	 * @param size
	 *            maximum size of the list to send
	 */
	public void listxattr(long req, long ino, int size);

	/**
	 * Remove an extended attribute
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            the inode number
	 * @param name
	 *            of the extended attribute
	 */
	public void removexattr(long req, long ino, String name);

	/**
	 * Check file access permissions
	 * 
	 * This will be called for the access() system call. If the
	 * 'default_permissions' mount option is given, this method is not called.
	 * 
	 * @param req
	 *            request handle
	 * @param ino
	 *            the inode number
	 * @param mask
	 *            requested access mode
	 */
	public void access(long req, long ino, int mask);

	/**
	 * Create and open a file
	 * 
	 * If the file does not exist, first create it with the specified mode, and
	 * then open it.
	 * 
	 * Open flags (with the exception of O_NOCTTY) are available in fi->flags.
	 * 
	 * Filesystem may store an arbitrary file handle (pointer, index, etc) in
	 * fi->fh, and use this in other all other file operations (read, write,
	 * flush, release, fsync).
	 * 
	 * There are also some flags (direct_io, keep_cache) which the filesystem
	 * may set in fi, to change the way the file is opened. See fuse_file_info
	 * structure in <fuse_common.h> for more details.
	 * 
	 * @param req
	 *            request handle
	 * @param parent
	 *            inode number of the parent directory
	 * @param name
	 *            to create
	 * @param mode
	 *            file type and mode with which to create the new file
	 * @param fi
	 *            file information
	 */
	public void create(long req, long parent, String name, short mode, long fi);
}
