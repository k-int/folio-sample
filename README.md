
#Dependencies

Java 8 JDK
Gradle
Docker

#Usage

To run the first sample module, inside a docker container execute `./run` from the root of this repository.

This will print an identifier for the container running the first module Docker image, to stop it running, execute `docker stop <identifier>`.

Whilst the module is running, it should be possible to visit http://localhost:9201 and receive a response similar to `{ "Message" : "Welcome to a sample Okapi module" }`
