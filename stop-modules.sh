#!/usr/bin/env bash
kill `ps | grep "[first|second]-module.jar" | awk '{print $1}'`
