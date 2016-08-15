gradle fatJar

cd first-module

Java -jar build/libs/first-module.jar 1>first-module.log 2>first-module.log &

cd ..

cd second-module

Java -jar build/libs/second-module.jar 1>second-module.log 2>second-module.log &

cd ..

cd third-module

Java -jar build/libs/third-module.jar 1>second-module.log 2>second-module.log &

cd ..
