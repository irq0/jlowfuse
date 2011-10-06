#!/bin/sh
mountpoint=$1

(
    cd dist
    echo "Mounting..."
    java -Djava.library.path=. -jar objectfs-async.jar $mountpoint
    ret=$?
    echo
    echo "Return code: $ret"
    echo "... umounting"
    fusermount -u $mountpoint

)
