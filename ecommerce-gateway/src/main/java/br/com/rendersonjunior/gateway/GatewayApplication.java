package br.com.rendersonjunior.gateway;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${USER_API_URL:http://localhost:8080}")
    private String userApiUrl;

    @Value("${PRODUCT_API_URL:http://localhost:8081}")
    private String productApiUrl;

    @Value("${SHOPPING_API_URL:http://localhost:8082}")
    private String shoppingApiUrl;

    @Bean
    public RouteLocator customRouteLocator(final RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user_route", r -> r.path("/user/**")
                        .uri(userApiUrl))
                .route("product_route", r -> r.path("/product/**")
                        .uri(productApiUrl))
                .route("shopping_route", r -> r.path("/shopping/**")
                        .uri(shoppingApiUrl))
                .build();
    }

}
