FROM java:alpine
MAINTAINER Marc Johnson <marc@k-int.co.uk>
COPY /build/libs/second-module.jar .
RUN apk add --update curl
EXPOSE 9202
CMD ["java", "-jar", "./second-module.jar", "1>server.log", "2>server.log"]

