package com.velykorod.productcatalogue.controllers.impl;

import com.velykorod.productcatalogue.controllers.CatalogController;
import com.velykorod.productcatalogue.persistance.domain.impl.Product;
import com.velykorod.productcatalogue.service.ProductService;
import com.velykorod.productcatalogue.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CatalogControllerImpl implements CatalogController {
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/catalog")
    @Override
    public String getCatalog(Model model) {
        model.addAttribute("products", productService.findAll());
        return "catalog";
    }

    @GetMapping("/catalog/product/{id}")
    @Override
    public String productDetails(@PathVariable String id, Model model) {
        Product product = productService.findById(Long.valueOf(id));
        if(product!=null) {
            model.addAttribute("product", product);
            return "product";
        }else{
            return "error";
        }
    }
}
