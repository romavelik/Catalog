package com.velykorod.productcatalogue.controllers;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductController {
    String deleteProduct(String id);
    String updateProduct(String id, Model model);
    String createProduct(Model model);
    String addProduct(String name, String description, String id, String price, MultipartFile image, MultipartFile file);
    String editProduct(String id, String name, String description, String price);
    byte [] getFile(/*String id,*/ String filename, String productName) throws IOException;
}
