package com.velykorod.productcatalogue.service;

import com.velykorod.productcatalogue.persistance.domain.impl.Product;

import java.util.List;

public interface ProductService {
   Iterable<Product> findAll();

   List<Product> findByName(String name);

   Product findById(Long id);

   void addProduct(Product product);

   void deleteProduct(Long id);

   void editProduct(Product product);
}
