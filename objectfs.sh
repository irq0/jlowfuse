#!/bin/sh
(
    cd dist
    echo "Mounting..."
    java -Djava.library.path=. -jar objectfs.jar -osubtype=bla -d
    ret=$?
    echo
    echo "Return code: $ret"
    echo "... umounting"
    sudo umount /mnt1

)
