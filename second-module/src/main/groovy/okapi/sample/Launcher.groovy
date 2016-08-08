package okapi.sample;

import io.vertx.groovy.core.Vertx;

import java.util.concurrent.CompletableFuture;

public class Launcher {
  private static Vertx vertx;

  public static void main(String[] args) {

    System.out.println("Server Starting");

    CompletableFuture deployed = start();

    deployed.join();

    System.out.println("Server Started");
  }

  public static CompletableFuture start() {
    vertx = Vertx.vertx();

    CompletableFuture deployed = new CompletableFuture();

    ApiVerticle.deploy(vertx, deployed);

    return deployed;
  }

  public static void stop()
  {
    if (vertx != null) { vertx.close(); }
  }
}
