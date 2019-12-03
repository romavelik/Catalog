package com.velykorod.productcatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*Works with in-memory DB therefore tha catalog is empty*/

@SpringBootApplication
public class ProductCatalogueApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogueApplication.class, args);
	}
}
