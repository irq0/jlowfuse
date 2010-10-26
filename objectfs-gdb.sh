#!/bin/sh
TMP=$(tempfile)

(
gdb -x 
    cd build/java
    echo "Mounting..."

    echo "set args -Djava.library.path=.. objectfs/ObjectFs -osubtype=bla -d" > $TMP
    echo "run" >> $TMP
    

    gdb -x $TMP java

    ret=$?
    echo
    echo "Return code: $ret"
    echo "... umounting"
    sudo umount /mnt1

)
