package com.velykorod.productcatalogue.controllers;

import org.springframework.ui.Model;

public interface CatalogController {
    String getCatalog(Model model);
    String productDetails(String id, Model model);
    String openCategory(String id, Model model);

}
