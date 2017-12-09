#!/usr/bin/env bash

mvn clean package shade:shade

STATUS=$?

if [ ${STATUS} -eq 0 ]; then
    echo "BUILD SUCCESSFUL, LAUNCHING TEST ENVIRONMENT"
    mv target/GameCraft.jar test-server/plugins/
    cd test-server
    java -jar server.jar
else
    echo "BUILD FAILED!"
fi