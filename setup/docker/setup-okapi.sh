curl -w '\n' -X POST -D - \
  -H "Content-type: application/json" \
  -d @./second-module-deployment.json  \
  http://localhost:9130/_/deployment/modules

  curl -w '\n' -X POST -D -   \
      -H "Content-type: application/json"   \
      -d @./second-module-discovery.json \
     http://localhost:9130/_/proxy/modules

   curl -w '\n' -X POST -D - \
     -H "Content-type: application/json" \
     -d @./sample-tenant.json  \
     http://localhost:9130/_/proxy/tenants

   curl -w '\n' -X POST -D - \
     -H "Content-type: application/json" \
     -d @./activate-second-module.json  \
     http://localhost:9130/_/proxy/tenants/our/modules
