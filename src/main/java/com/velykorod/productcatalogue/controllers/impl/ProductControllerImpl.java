package com.velykorod.productcatalogue.controllers.impl;

import com.velykorod.productcatalogue.controllers.ProductController;
import com.velykorod.productcatalogue.persistance.domain.impl.Product;
import com.velykorod.productcatalogue.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class ProductControllerImpl implements ProductController {
    @Autowired
    private ProductServiceImpl productService;


    @PostMapping("/create")
    @Override
    public String addProduct(@RequestParam("name") String name, @RequestParam("description") String description) {
        Product product = new Product(name, description, new Date());
        productService.addProduct(product);
        return "redirect:/catalog";
    }

    @GetMapping("/create_product")
    @Override
    public String createProduct() {
        return "create_product";
    }


    @PostMapping("/delete_product")
    @Override
    public String deleteProduct(@RequestParam String id) {
        productService.deleteProduct(Long.valueOf(id));
        return "redirect:/catalog";
    }

    @GetMapping("/update_product/{id}")
    @Override
    public String updateProduct(@PathVariable String id, Model model) {
        model.addAttribute("product", productService.findById(Long.valueOf(id)));
        return "update_product";
    }

    @PostMapping("/update")
    @Override
    public String editProduct(@RequestParam String id, @RequestParam String name, @RequestParam String description) {
        Product product = new Product(Long.valueOf(id), name, description, new Date());
        productService.editProduct(product);
        return "redirect:/catalog";
    }
}
