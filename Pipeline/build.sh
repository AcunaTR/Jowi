#!/bin/bash

function buildFail {
    echo "Maven build failed"
}
trap buildFail ERR

CURDIR=`pwd`

echo " Current Directory is "
echo ${CURDIR}

docker run --rm \
           -v "${CURDIR}/:/build" \
           --workdir /build/Janra.Jowi \
           maven:3.5.2-jdk-8 mvn compile