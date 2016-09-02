#!/usr/bin/env bash
#docker ps | grep "[first|second]-module" | awk '{print $1}' | docker stop
docker stop `docker ps | grep "[first|second]-module" | awk '{print $1}'`
docker rm `docker ps -a | grep "[first|second]-module" | awk '{print $1}'`
