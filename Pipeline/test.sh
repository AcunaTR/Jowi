#!/bin/sh

function testFail {
    echo "Maven test failed"
}
trap testFail ERROR

CURDIR=`pwd`

docker run --rm \
           -v "${CURDIR}/:/test" \
           --workdir /test/Janra.Jowi \
           maven:3.5.2-jdk-8 mvn test