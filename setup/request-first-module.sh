curl -w '\n' -D -  \
  -H "X-Okapi-Tenant: our" \
  http://localhost:9130/first-module

  curl -w '\n' -D -  \
    -H "X-Okapi-Tenant: our" \
    http://localhost:9130/first-module/resource
