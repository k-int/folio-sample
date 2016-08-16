#!/usr/bin/env bash

 curl -w '\n' -X POST -D - \
   -H "Content-type: application/json" \
   -d @./sample-tenant.json  \
   http://localhost:9130/_/proxy/tenants

  curl -w '\n' -X POST -D -   \
    -H "Content-type: application/json"   \
    -d @./second-module-discovery.json \
   http://localhost:9130/_/discovery/modules

  curl -w '\n' -D - -s \
    -X POST \
    -H "Content-type: application/json" \
    -d @./second-module-proxy.json  \
    http://localhost:9130/_/proxy/modules

    curl -w '\n' -X POST -D -   \
      -H "Content-type: application/json"   \
      -d @./first-module-discovery.json \
     http://localhost:9130/_/discovery/modules

    curl -w '\n' -D - -s \
      -X POST \
      -H "Content-type: application/json" \
      -d @./first-module-proxy.json  \
      http://localhost:9130/_/proxy/modules

   curl -w '\n' -X POST -D - \
     -H "Content-type: application/json" \
     -d @./activate-first-module.json  \
     http://localhost:9130/_/proxy/tenants/our/modules

 curl -w '\n' -X POST -D - \
   -H "Content-type: application/json" \
   -d @./activate-second-module.json  \
   http://localhost:9130/_/proxy/tenants/our/modules
