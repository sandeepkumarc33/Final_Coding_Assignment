package com.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayCustomRoutes {
	

	@Bean
	public RouteLocator gateWayRouter(RouteLocatorBuilder builder) {
		
		return builder.routes()
				.route(p -> p.path("/category/**")
				.uri("lb://category-service"))
				
				.route(p -> p.path("/product/**")
						.uri("lb://product-service"))
				.build();
	}
	
}
