/* FUSE session functions */
%module Session
%{
#ifndef SWIG_FUSE_SESSION_I
#define SWIG_FUSE_SESSION_I

#define FUSE_USE_VERSION 26
#include <fuse_lowlevel.h>
#include <fuse_opt.h>
#include <sys/statvfs.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <fcntl.h>
%}
%include "fuse_types.i"
%include "fuse_structs.i"

%include "various.i"
%include "buffers.i"


%rename(addChan) fuse_session_add_chan;
%rename(removeChan) fuse_session_remove_chan;
%rename(nextChan) fuse_session_next_chan;
%rename(exit) fuse_session_exit;
%rename(destroy) fuse_session_destroy;
%rename(reset) fuse_session_reset;
%rename(exited) fuse_session_exited;
%rename(loopSingle) fuse_session_loop;
%rename(loopMulti) fuse_session_loop_mt;

extern void fuse_session_add_chan(struct fuse_session *se,
                                  struct fuse_chan *ch);
extern void fuse_session_remove_chan(struct fuse_chan *ch);
extern struct fuse_chan *fuse_session_next_chan(struct fuse_session *se,
                                                struct fuse_chan *ch);
extern void fuse_session_exit(struct fuse_session *se);
extern void fuse_session_destroy(struct fuse_session *se);
extern void fuse_session_reset(struct fuse_session *se);
extern int fuse_session_exited(struct fuse_session *se);
extern int fuse_session_loop(struct fuse_session *se);
extern int fuse_session_loop_mt(struct fuse_session *se);

%{
#endif
%}
