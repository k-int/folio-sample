docker stop `docker ps | grep "first-module" | awk '{print $1}'`

docker stop `docker ps | grep "second-module" | awk '{print $1}'`
