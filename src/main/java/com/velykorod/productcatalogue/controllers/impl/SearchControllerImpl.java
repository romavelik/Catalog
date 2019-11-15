package com.velykorod.productcatalogue.controllers.impl;

import com.velykorod.productcatalogue.controllers.SearchController;
import com.velykorod.productcatalogue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchControllerImpl implements SearchController {

    @Autowired
    private ProductService productService;

    /*Finds product with specified name and returns list of products with this name*/

    @GetMapping("/search")
    @Override
    public String findByName(@RequestParam("search") String name, Model model) {
        model.addAttribute("products", productService.findByName(name));
        return "search_results";
    }
}
