package com.velykorod.productcatalogue.controllers.impl;

import com.velykorod.productcatalogue.controllers.MainController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControllerImpl implements MainController {

    /*Returns greeting page*/

    @GetMapping("/")
    @Override
    public String greeting(Model model) {
        return "main";
    }
}
