#!/bin/bash

function packageFail {
    echo "Maven package failed"
}
trap packageFail ERR

CURDIR=`pwd`

docker run --rm -v "${CURDIR}/:/build" --workdir /build/Janra.Jowi maven:3.5.2-jdk-8 mvn package
