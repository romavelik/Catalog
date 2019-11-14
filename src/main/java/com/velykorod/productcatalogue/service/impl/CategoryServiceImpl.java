package com.velykorod.productcatalogue.service.impl;

import com.velykorod.productcatalogue.persistance.domain.impl.Category;
import com.velykorod.productcatalogue.persistance.repository.CategoryRepository;
import com.velykorod.productcatalogue.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void editCategory(Category category) {
        categoryRepository.updateCategory(category.getName(), category.getId());
    }
}
