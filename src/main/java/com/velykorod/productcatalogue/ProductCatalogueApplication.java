package com.velykorod.productcatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/*Requires to start with redis-server running. Works with in-memory DB therefore tha catalog is empty*/

@EnableCaching
@SpringBootApplication
public class ProductCatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogueApplication.class, args);
	}

}
