package com.velykorod.productcatalogue.controllers.impl;

import com.velykorod.productcatalogue.controllers.ProductController;
import com.velykorod.productcatalogue.persistance.domain.impl.Category;
import com.velykorod.productcatalogue.persistance.domain.impl.Product;
import com.velykorod.productcatalogue.service.CategoryService;
import com.velykorod.productcatalogue.service.ProductService;
import com.velykorod.productcatalogue.service.impl.CategoryServiceImpl;
import com.velykorod.productcatalogue.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class ProductControllerImpl implements ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /*Saves new product in DB and redirects to product list page*/

    @PostMapping("/add_product")
    @Override
    public String addProduct(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("categoryId") String id) {
        Product product = new Product(name, description, new Date(), categoryService.findById(Long.valueOf(id)));
        productService.addProduct(product);
        return "redirect:/catalog";
    }

    /*Removes page where client can add new product*/

    @GetMapping("/create_product")
    @Override
    public String createProduct(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "create_product";
    }

    /*Removes specified product form DB and redirects to product list page*/

    @PostMapping("/delete_product")
    @Override
    public String deleteProduct(@RequestParam String id) {
        productService.deleteProduct(Long.valueOf(id));
        return "redirect:/catalog";
    }

    /*Returns page where client can edit specified product information*/

    @GetMapping("/update_product/{id}")
    @Override
    public String updateProduct(@PathVariable String id, Model model) {
        model.addAttribute("product", productService.findById(Long.valueOf(id)));
        return "update_product";
    }

    /*Updates product in DB and redirects to product list page*/

    @PostMapping("/update")
    @Override
    public String editProduct(@RequestParam String id, @RequestParam String name, @RequestParam String description) {
        Product product = new Product(Long.valueOf(id), name, description, new Date());
        productService.editProduct(product);
        return "redirect:/catalog";
    }
}
