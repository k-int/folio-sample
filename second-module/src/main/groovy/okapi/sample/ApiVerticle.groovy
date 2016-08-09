package okapi.sample

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.http.HttpMethod
import io.vertx.core.http.HttpServer
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.groovy.core.Vertx

import java.util.concurrent.CompletableFuture

public class ApiVerticle extends AbstractVerticle {

    private HttpServer server;

    public static void deploy(Vertx vertx, CompletableFuture deployed) {

        vertx.deployVerticle("okapi.sample.ApiVerticle", ["worker" : true], { res ->
            if (res.succeeded()) {
                deployed.complete(null);
            } else {
                deployed.completeExceptionally(res.cause());
            }
        });
    }

    @Override
    public void start(Future deployed) {
        server = vertx.createHttpServer()

        def router = Router.router(vertx)

        router.route(HttpMethod.GET, "/second-module/resource").handler(this.&getResource)
        router.route(HttpMethod.GET, "/second-module").handler(this.&handleRoot)

        server.requestHandler(router.&accept)
                .listen(9202,
                { result ->
                    if (result.succeeded()) {
                        deployed.complete();
                    } else {
                        deployed.fail(result.cause());
                    }
                })
    }

    @Override
    public void stop() {
        server.close();
    }

    public void handleRoot(RoutingContext routingContext) {

        outputHeaders routingContext

        success(routingContext.response(),
                new JsonObject().put("Message", "Welcome to a sample Okapi module"))
    }

    private outputHeaders(RoutingContext routingContext) {
        println "Headers Received"

        for (Map.Entry<String, String> entry : routingContext.request().headers().entries()) {
            System.out.format("%s : %s\n", entry.getKey(), entry.getValue())
        }

        println "End of Headers Received"
        println ""
        println ""
    }

    public void getResource(RoutingContext routingContext) {
        def resource = new JsonObject();

        resource.put "Name", "My Other Interesting Resource"

        success(routingContext.response(), resource)
    }

    private success(response, body) {
        def json = Json.encodePrettily(body)

        response.putHeader "content-type", "application/json"
        response.putHeader "content-length", Integer.toString(json.length())

        response.write json
        response.end()
    }

}
