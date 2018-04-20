#!/bin/bash

function deployFail {
    echo "Deploy to AWS Lambda failed"
}
trap deployFail ERR

VERSION=$(cat ./version)

docker tag jowi:latest 015887481462.dkr.ecr.us-east-1.amazonaws.com/acuna-jowi:latest
docker tag 015887481462.dkr.ecr.us-east-1.amazonaws.com/acuna-jowi:latest 015887481462.dkr.ecr.us-east-1.amazonaws.com/acuna-jowi:$VERSION.$GO_PIPELINE_LABEL
$(aws ecr get-authorization-token --region us-east-1 --output text --query 'authorizationData[].authorizationToken' | base64 -d | cut -d: -f2)
docker push 015887481462.dkr.ecr.us-east-1.amazonaws.com/acuna-jowi:latest
docker push 015887481462.dkr.ecr.us-east-1.amazonaws.com/acuna-jowi:$VERSION.$GO_PIPELINE_LABEL

