package com.jmy.app;

import com.jmy.app.resources.HelloWorldResouce;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloServiceApplication extends Application<HelloServiceConfiguration> {
  public static void main(final String[] args) throws Exception {
    new HelloServiceApplication().run(args);
  }

  @Override
  public String getName() {
    return "hello-service";
  }

  @Override
  public void initialize(final Bootstrap<HelloServiceConfiguration> bootstrap) {
    // TODO: application initialization
  }

  @Override
  public void run(final HelloServiceConfiguration configuration,
                  final Environment environment) {
    environment.jersey().register(new HelloWorldResouce());
    // TODO: implement application
  }

}

//java -jar target/hello-world-0.0.1-SNAPSHOT.jar
//java -jar target/rest-service-1.0-SNAPSHOT.jar server config.yml