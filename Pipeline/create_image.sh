#!/bin/bash

function createFail {
    echo "Creation of docker image failed"
}
trap createFail ERR

VERSION=$(cat ./version)

docker build -t jowi:latest .
docker tag jowi:latest jowi:$VERSION.$BUILD_NUMBER
