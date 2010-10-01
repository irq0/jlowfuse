#define FUSE_USE_VERSION 26

#include <fuse_lowlevel.h>
#include <fuse_opt.h>

#include <sys/statvfs.h>

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <fcntl.h>


void jlowfuse_init(void *userdata, struct fuse_conn_info *conn) 
{
        printf("yeeeeeehaa!");

        
}


static struct fuse_lowlevel_ops jlowfuse_opts = {
          .init        = jlowfuse_init,
          .destroy     = NULL,
          .lookup      = NULL,
          .forget      = NULL,
          .getattr     = NULL,
          .setattr     = NULL,
          .readlink    = NULL,
          .mknod       = NULL,
          .mkdir       = NULL,
          .unlink      = NULL,
          .rmdir       = NULL,
          .symlink     = NULL,
          .rename      = NULL,
          .link        = NULL,
          .open        = NULL,
          .read        = NULL,
          .write       = NULL,
          .flush       = NULL,
          .release     = NULL,
          .fsync       = NULL,
          .opendir     = NULL,
          .readdir     = NULL,
          .releasedir  = NULL,
          .fsyncdir    = NULL,
          .statfs      = NULL,
          .setxattr    = NULL,
          .getxattr    = NULL,
          .listxattr   = NULL,
          .removexattr = NULL,
          .access      = NULL,
          .create      = NULL,
          .getlk       = NULL,
          .setlk       = NULL,
          .bmap        = NULL,
          /* .ioctl       = NULL,*/
          /* .poll        = NULL, */
};


JNIEXPORT int JNICALL Java_JLowFuse_init(JNIEnv *env, jclass cls,  ) 
{
        struct fuse_chan *chan;
        struct fuse_args args = FUSE_ARGS_INIT(argc, argv);
        struct fuse_session *sess; 
        char *mount_point;
        int multi_threaded = -1;
        int foreground = -1;
        int err = -1;

        /* parse commandline options */
        if (fuse_parse_cmdline(&args, &mount_point, &multi_threaded, &foreground) != 0) {        
                //    printf("cannot parse commandline :(");
                return 2;
        }

        if (!mount_point) {
                printf("specify mountpoint\n");
                return 2;
        }
        
        chan = fuse_mount(mount_point, &args);
        sess = fuse_lowlevel_new(&args, &jlowfuse_ops, sizeof(jlowfuse_ops), NULL);
        
        printf("chan=%p sess=%p\n", chan, sess);
        
        fuse_set_signal_handlers(sess);
        
        fuse_session_add_chan(sess, chan);
        err = fuse_session_loop(sess);
        
        fuse_remove_signal_handlers(sess);
        fuse_session_remove_chan(chan);
        
        fuse_session_destroy(sess);
        fuse_session_exit(sess);
        
        fuse_unmount(mount_point, chan);
        
        fuse_opt_free_args(&args);
        
        return err;
}

