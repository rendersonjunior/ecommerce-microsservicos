package br.com.rendersonjunior.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

	final static String uriUser = "http://localhost:8080";
	final static String uriProduct = "http://localhost:8081";
	final static String uriShopping = "http://localhost:8082";

	@Bean
	public RouteLocator customRouteLocator(final RouteLocatorBuilder builder) {
		return builder.routes()
				.route("user_route", r -> r.path("/user/**")
						.uri(uriUser))
				.route("product_route", r -> r.path("/product/**")
						.uri(uriProduct))
				.route("shopping_route", r -> r.path("/product/**")
						.uri(uriShopping))
				.build();
	}


}
