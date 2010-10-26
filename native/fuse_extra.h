#ifndef FUSE_EXTRA_H
#define FUSE_EXTRA_H

struct dirbuf {
	void *p;
	size_t size;
};

int fuse_extra_reply_buf_limited(fuse_req_t req, const char *buf, size_t bufsize,
                                 off_t off, size_t maxsize);
void fuse_extra_dirbuf_add(fuse_req_t req, struct dirbuf *b, const char *name,
                           fuse_ino_t ino, mode_t mode);

#endif
