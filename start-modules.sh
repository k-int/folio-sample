#!/usr/bin/env bash

gradle fatJar

Java -jar first-module/build/libs/first-module.jar 1>first-module.log 2>first-module.log &

Java -jar second-module/build/libs/second-module.jar 1>second-module.log 2>second-module.log &

