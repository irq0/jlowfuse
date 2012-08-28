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
