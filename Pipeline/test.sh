#!/bin/bash
set -e

function testFail {
    echo "Maven test failed"
}
trap testFail ERR

CURDIR=`pwd`

echo "this is before it runs"

docker run --rm -v "${CURDIR}/:/test" --workdir /test/Janra.Jowi maven:3.5.2-jdk-8 mvn test