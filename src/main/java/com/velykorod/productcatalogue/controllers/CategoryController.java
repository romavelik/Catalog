package com.velykorod.productcatalogue.controllers;

import org.springframework.ui.Model;

public interface CategoryController {
    String addCategory(String name);
    String createCategory();
    String deleteCategory(String id);
    String editCategory(String id, Model model);
    String updateCategory(String id, String name);
}
