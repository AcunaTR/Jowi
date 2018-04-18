#!/bin/sh

function testFail{

    echo "Failed as planned"
}

testFail ERR


pwd
ls -al
ps

echo "this should fail!"