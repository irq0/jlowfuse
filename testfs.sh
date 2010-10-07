#!/bin/sh
(
    cd dist
    echo "Mounting..."
    java -Djava.library.path=. -jar testfs.jar
    echo
    echo 
    echo "... umounting"
    sudo umount /mnt1

)
