package com.velykorod.productcatalogue.controllers.impl;

import com.velykorod.productcatalogue.controllers.CategoryController;
import com.velykorod.productcatalogue.persistance.domain.impl.Category;
import com.velykorod.productcatalogue.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryControllerImpl implements CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/add_category")
    @Override
    public String addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryService.addCategory(category);
        return "redirect:/catalog";
    }

    @GetMapping("/create_category")
    @Override
    public String createCategory() {
        return "create_category";
    }

    @PostMapping("/delete_category")
    @Override
    public String deleteCategory(@RequestParam("id") String id){
        categoryService.deleteCategory(Long.valueOf(id));
        return "redirect:/catalog";
    }

    @GetMapping("/edit_category/{id}")
    @Override
    public String editCategory(@PathVariable String id, Model model) {
        model.addAttribute(categoryService.findById(Long.valueOf(id)));
        return "edit_category";
    }

    @PostMapping("/update_category")
    @Override
    public String updateCategory(@RequestParam("id") String id, @RequestParam("name") String name) {
        Category category = new Category();
        category.setId(Long.valueOf(id));
        category.setName(name);
        categoryService.editCategory(category);
        return "redirect:/catalog";
    }
}
