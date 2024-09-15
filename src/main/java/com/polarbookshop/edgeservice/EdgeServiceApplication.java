package com.polarbookshop.edgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EdgeServiceApplication {

	public static void main(String[] args) {
		/*ApplicationContext context = */SpringApplication.run(EdgeServiceApplication.class, args);

//		// Environment 객체를 통해 설정 값에 접근
//		Environment env = context.getEnvironment();
//
//		// 특정 라우트 ID에 대한 URI 및 Predicates 정보 출력
//		String catalogServiceUri = env.getProperty("spring.cloud.gateway.routes[0].uri");
//		String[] catalogServicePredicates = env.getProperty("spring.cloud.gateway.routes[0].predicates[0]", String[].class);
//		System.out.println("Catalog Service URI: " + catalogServiceUri);
//		System.out.println("Catalog Service Predicates: " + Arrays.toString(catalogServicePredicates));
//
//		String orderServiceUri = env.getProperty("spring.cloud.gateway.routes[1].uri");
//		String[] orderServicePredicates = env.getProperty("spring.cloud.gateway.routes[1].predicates[0]", String[].class);
//		System.out.println("Order Service URI: " + orderServiceUri);
//		System.out.println("Order Service Predicates: " + Arrays.toString(orderServicePredicates));
	}
}
