#!/usr/bin/env bash

mvn clean package shade:shade

STATUS=$?

if [ ${STATUS} -eq 0 ]; then
    echo "BUILD SUCCESSFUL!"

    if [ -d "server/" ]; then
        echo "SERVER DIRECTORY FOUND! LAUNCHING SERVER!"
        mv target/GameCraft.jar server/plugins/
        cd server
        java -jar server.jar
    else
        echo "NO SERVER DIRECTORY DETECTED! NOT LAUNCHING SERVER!"
    fi
else
    echo "BUILD FAILED!"
fi