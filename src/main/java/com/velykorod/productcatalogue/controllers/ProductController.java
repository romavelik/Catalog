package com.velykorod.productcatalogue.controllers;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductController {
    String deleteProduct(String id, String name);
    String updateProduct(String id, Model model);
    String createProduct(Model model);
    String addProduct(String name, String description, String id, String price, MultipartFile image, MultipartFile [] files);
    String editProduct(String id, String name, String description, String price, String oldName);
    byte [] downloadProduct(String filename) throws IOException;
}
