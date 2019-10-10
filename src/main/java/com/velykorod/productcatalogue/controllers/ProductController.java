package com.velykorod.productcatalogue.controllers;

import org.springframework.ui.Model;

public interface ProductController {
    String deleteProduct(String id);
    String updateProduct(String id, Model model);
    String createProduct();
    String addProduct(String name, String description);
    String editProduct(String id, String name, String description);
}
