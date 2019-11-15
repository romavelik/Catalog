package com.velykorod.productcatalogue.controllers.impl;

import com.velykorod.productcatalogue.controllers.CatalogController;
import com.velykorod.productcatalogue.persistance.domain.impl.Category;
import com.velykorod.productcatalogue.persistance.domain.impl.Product;
import com.velykorod.productcatalogue.service.CategoryService;
import com.velykorod.productcatalogue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CatalogControllerImpl implements CatalogController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /*Returns page with product list*/

    @GetMapping("/catalog")
    @Override
    public String getCatalog(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "catalog";
    }

    /*Returns specified product page that contains details and buttons update and delete*/

    @GetMapping("/catalog/product/{id}")
    @Override
    public String productDetails(@PathVariable String id, Model model) {
        Product product = productService.findById(Long.valueOf(id));
        if (product != null) {
            model.addAttribute("product", product);
            return "product";
        } else {
            return "error";
        }
    }

    @GetMapping("/catalog/category/{id}")
    public String openCategory(@PathVariable String id, Model model){
        Category category = categoryService.findById(Long.valueOf(id));
        if (category != null){
            model.addAttribute("products", category.getProducts());
            return "category";
        } else {
            return "error";
        }
    }
}
