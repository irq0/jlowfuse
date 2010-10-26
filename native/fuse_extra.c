#define FUSE_USE_VERSION 26
#include <fuse_lowlevel.h>
#include <string.h>
#include <stdlib.h>
#include <err.h>

struct dirbuf {
	void *p;
	size_t size;
};

void fuse_extra_dirbuf_add(fuse_req_t req, struct dirbuf *b, const char *name,
		       fuse_ino_t ino)
{
	struct stat stbuf;
	size_t oldsize = b->size;
	b->size += fuse_add_direntry(req, NULL, 0, name, NULL, 0);
        char *newp = realloc(b->p, b->size);
        if (!newp) {
                errx(26, "fuse_extra_dirbuf_add: cannot allocate memory");
        }
	b->p = newp;
	memset(&stbuf, 0, sizeof(stbuf));
	stbuf.st_ino = ino;
	fuse_add_direntry(req, b->p + oldsize, b->size - oldsize, name, &stbuf,
			  b->size);
}

#define min(x, y) ((x) < (y) ? (x) : (y))

int fuse_extra_reply_buf_limited(fuse_req_t req, const char *buf, size_t bufsize,
			     off_t off, size_t maxsize)
{
	if (off < bufsize)
		return fuse_reply_buf(req, buf + off,
				      min(bufsize - off, maxsize));
	else
		return fuse_reply_buf(req, NULL, 0);
}