
#Goal

This sample aims to demonstrate communication between two modules, via Okapi. The first module makes a simple request to the second and embeds the response in its response.

#Prerequisites

Java 8 JDK
Gradle 3.0i-rc-2
Groovy 2.4.7
Docker (only if hosting modules in containers, not currently working)

#Preparation

If sdkman (http://sdkman.io/) is installed, run `source ./setup-environment.sh` to setup the shell with the appropriate versions of Gradle and Groovy.

Make sure that Okapi is running on it's default port of 9130 (see https://github.com/sling-incubator/okapi/blob/master/doc/guide.md for details).

#Running the Modules

To run the modules, execute `./start-modules.sh` from the root. To stop them, execute `./stop-modules.sh`.

#Registering the Modules With Okapi

From the setup directory, run `./setup-okapi.sh` this will configure Okapi with the relevant module configuration and set up a test tenant.

#Using the Modules

Then it is possible to make requests to the two modules, as demonstrated in the request-second-module.sh and request-first-module.sh scripts.


