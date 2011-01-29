#!/usr/bin/env python

import sys
import os.path

def errno_class(errno, name):
    return """
package jlowfuse.exceptions;

import fuse.Errno;

public class %(name)s extends FuseException {
    static final long serialVersionUID = 42;
    public %(name)s() {
        super(Errno.%(errno)s);
    }
}
""" % {'name': name, 'errno': errno }



def main():

    filename = sys.argv[1]
    outdir = sys.argv[2]

    file = open(filename, "r")

    for line in file:
        if not line.startswith("#define\tE"):
            continue

        values = line.split()

        errno_name = values[1]
        comment = values[4:-1]
        

        descr = "".join(map(lambda x: x.capitalize(), comment))
        descr = descr.replace("-", "").replace("/","").replace("\\", "").replace(".","").replace(";","").replace(":","")

        if len(descr) == 0:
            continue

        out = open(os.path.join(outdir, "%s.java" % (descr,)), "w" )
        out.write(errno_class(errno_name, descr))
        out.close()


if __name__ == '__main__': main()

