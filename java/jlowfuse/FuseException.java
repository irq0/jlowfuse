package jlowfuse.exceptions;

public class FuseException extends Exception {
	int errno = 0;

	private static final long serialVersionUID = -7429088074385678308L;

	public FuseException(int errno) {
		this.errno = errno;
	}

	public int getErrno() {
		return errno;
	}
}
