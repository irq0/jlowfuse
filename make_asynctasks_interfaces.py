#!/usr/bin/env python

import sys
import os.path

def make_interface(name, attribs):
    return """
package jlowfuse.async.tasks;

import jlowfuse.FuseReq;

public interface %(name)s {
	public void attributes(%(attribs)s);
}
""" % {'name': name, 'attribs': attribs }



def main():

    filename = sys.argv[1]
    outdir = sys.argv[2]

    file = open(filename, "r")

    for line in file:
        if not "public void" in line:
            continue

        line = line.replace("public void", "")

        split = line.split("(")

        name = split[0].strip().capitalize()
        params = "".join(split[1:]).replace(")","").replace(";","").strip()

        with open(os.path.join(outdir, "%s.java" % (name,)), "w" ) as out:
            out.write(make_interface(name, params))



if __name__ == '__main__': main()
