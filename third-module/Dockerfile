FROM java:alpine
MAINTAINER Marc Johnson <marc@k-int.co.uk>
COPY /build/libs/third-module.jar .
EXPOSE 9203
CMD ["java", "-jar", "./third-module.jar", "1>server.log", "2>server.log"]

