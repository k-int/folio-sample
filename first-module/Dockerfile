FROM java:alpine
MAINTAINER Marc Johnson <marc@k-int.co.uk>
COPY /build/libs/first-module.jar .
RUN apk add --update curl
EXPOSE 9201
CMD ["java", "-jar", "./first-module.jar", "1>server.log", "2>server.log"]

