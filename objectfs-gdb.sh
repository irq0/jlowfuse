#!/bin/sh
TMP=$(tempfile)
$mountpoint=$1
(
gdb -x 
    cd build/java
    echo "Mounting..."

    echo "set args -Djava.library.path=.. objectfs/ObjectFs $mountpoint" > $TMP
    echo "run" >> $TMP
    

    gdb -x $TMP java

    ret=$?
    echo
    echo "Return code: $ret"
    echo "... umounting"
    fusermount -u $mountpoint

)
