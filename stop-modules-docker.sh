#!/usr/bin/env bash
docker stop `docker ps | grep "[first|second]-module" | awk '{print $1}'`
