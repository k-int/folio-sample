package okapi.sample;

import io.vertx.core.*;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.*;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ApiVerticle extends AbstractVerticle {

    private HttpServer server;

    public static void deploy(Vertx vertx, CompletableFuture<Void> deployed) {

        DeploymentOptions options = new DeploymentOptions();

        options.setWorker(true);

        vertx.deployVerticle("okapi.sample.ApiVerticle", options, res -> {
            if (res.succeeded()) {
                deployed.complete(null);
            } else {
                deployed.completeExceptionally(res.cause());
            }
        });
    }

    @Override
    public void start(Future<Void> deployed) {
        server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route(HttpMethod.GET, "/first-module/resource").handler(this::getResource);
        router.route(HttpMethod.GET, "/first-module").handler(this::handleRoot);

        server.requestHandler(router::accept)
                .listen(9201,
                result -> {
                    if (result.succeeded()) {
                        deployed.complete();
                    } else {
                        deployed.fail(result.cause());
                    }
                });
    }

    @Override
    public void stop() {
        server.close();
    }

    public void handleRoot(RoutingContext routingContext) {

        outputHeaders(routingContext);

        System.out.println("End of Headers Received");
        System.out.println("");
        System.out.println("");

        success(routingContext.response(),
                new JsonObject().put("Message", "Welcome to a sample Okapi module"));
    }

    private void outputHeaders(RoutingContext routingContext) {
        System.out.println("Headers Received");

        for (Map.Entry<String, String> entry : routingContext.request().headers().entries()) {
            System.out.format("%s : %s\n", entry.getKey(), entry.getValue());
        }
    }

    public void getResource(RoutingContext routingContext) {

        outputHeaders(routingContext);

        HttpClient client = routingContext.vertx().createHttpClient();

        

        client.getAbs("http://localhost:9130/second-module/resource",
            response -> {
                response.bodyHandler(buffer -> {
                    JsonObject resource = new JsonObject();

                    resource.put("Name", "My Interesting Resource");

                    resource.put("OtherResource", new JsonObject(buffer.getString(0, buffer.length())));

                    success(routingContext.response(), resource);
                });
            }).putHeader("X-Okapi-Tenant", "our").end();
    }

    private void success(io.vertx.core.http.HttpServerResponse response, Object body) {
        String json = Json.encodePrettily(body);

        response.putHeader("content-type", "application/json");
        response.putHeader("content-length", Integer.toString(json.length()));

        response.write(json);
        response.end();
    }

}
