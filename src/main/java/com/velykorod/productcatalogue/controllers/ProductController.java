package com.velykorod.productcatalogue.controllers;

import com.velykorod.productcatalogue.persistance.domain.impl.Category;
import org.springframework.ui.Model;

public interface ProductController {
    String deleteProduct(String id);
    String updateProduct(String id, Model model);
    String createProduct(Model model);
    String addProduct(String name, String description, String id);
    String editProduct(String id, String name, String description);
}
