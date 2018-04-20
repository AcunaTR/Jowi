#!/bin/bash

function deployFail {
    echo "Deploy to AWS Lambda failed"
}
trap deployFail ERR

VERSION=$(cat ./version)

docker tag jowi:latest 015887481462.dkr.ecr.us-east-1.amazonaws.com/acuna-jowi:latest
docker tag 015887481462.dkr.ecr.us-east-1.amazonaws.com/acuna-jowi:latest 015887481462.dkr.ecr.us-east-1.amazonaws.com/acuna-jowi:$VERSION.$BUILD_NUMBER
$(aws ecr get-authorization-token --region us-east-1 --output text --query 'authorizationData[].authorizationToken' | base64 -d | cut -d: -f2)
docker login -u AWS 015887481462.dkr.ecr.us-east-1.amazonaws.com
docker push 015887481462.dkr.ecr.us-east-1.amazonaws.com/acuna-jowi:latest
docker push 015887481462.dkr.ecr.us-east-1.amazonaws.com/acuna-jowi:$VERSION.$BUILD_NUMBER

#aws sns publish --region "us-east-1" --topic-arn  "arn:aws:sns:us-east-1:015887481462:AcunaBob" --message "This should be working..."