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

#define FUSE_USE_VERSION 26
#include <fuse_lowlevel.h>
#include <string.h>
#include <stdlib.h>
#include <err.h>

#include "fuse_extra.h"

void fuse_extra_dirbuf_add(fuse_req_t req, struct dirbuf *b, const char *name,
                           fuse_ino_t ino, mode_t mode)
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
        stbuf.st_mode = mode;
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
