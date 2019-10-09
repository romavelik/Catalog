package com.velykorod.productcatalogue.controllers;

import org.springframework.ui.Model;

public interface SearchController {
    String findByName(String name, Model model);
}
