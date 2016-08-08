package okapi.sample;

import com.google.common.collect.ImmutableMap;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.groovy.core.Vertx;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ApiVerticle extends AbstractVerticle {

    private HttpServer server;

    public static void deploy(Vertx vertx, CompletableFuture<Void> deployed) {

        vertx.deployVerticle("okapi.sample.ApiVerticle", ImmutableMap.of("worker", true), res -> {
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

        registerRootRoute(router);

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

        System.out.println("Headers Received");

        for (Map.Entry<String, String> entry : routingContext.request().headers().entries()) {
            System.out.format("%s : %s\n", entry.getKey(), entry.getValue());
        }

        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json");

        response.end("{ \"Message\" : \"Welcome to a sample Okapi module\" }");
    }

    public Route registerRootRoute(Router router) {
        return router.route().handler(this::handleRoot);
    }
}
