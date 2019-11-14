package com.velykorod.productcatalogue.service;

import com.velykorod.productcatalogue.persistance.domain.impl.Category;
import com.velykorod.productcatalogue.persistance.domain.impl.Product;

import java.util.List;

public interface CategoryService {
    Iterable<Category> findAll();

    Category findById(Long id);

    void addCategory(Category category);

    void deleteCategory(Long id);

    void editCategory(Category category);
}
