package okapi.sample;

import io.vertx.core.Vertx;
import java.util.concurrent.CompletableFuture;

public class Launcher {
  public static void main(String[] args) {

    System.out.println("Server Starting");

    CompletableFuture<Void> deployed = new CompletableFuture<Void>();

    Vertx vertx = Vertx.vertx();

    ApiVerticle.deploy(vertx, deployed);

    deployed.join();

    System.out.println("Server Started");
  }
}
