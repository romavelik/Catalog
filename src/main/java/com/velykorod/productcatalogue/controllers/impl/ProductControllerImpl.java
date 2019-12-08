package com.velykorod.productcatalogue.controllers.impl;

import com.velykorod.productcatalogue.controllers.ProductController;
import com.velykorod.productcatalogue.persistance.domain.impl.Product;
import com.velykorod.productcatalogue.service.CategoryService;
import com.velykorod.productcatalogue.service.ProductService;
import com.velykorod.productcatalogue.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Controller
public class ProductControllerImpl implements ProductController {
    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add_product")
    @Override
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("categoryId") String id,
                             @RequestParam("price") String price,
                             @RequestParam("image") MultipartFile image,
                             @RequestParam("file") MultipartFile file) {
        String resultImageName = UUID.randomUUID().toString() + "." + image.getOriginalFilename();
        String resultFileName = Math.random() + "." + file.getOriginalFilename();
        Product product = new Product(name, description, new Date(), categoryService.findById(Long.valueOf(id)));
        product.setPrice(BigDecimal.valueOf(new Double((price))));
        product.setImgName(resultImageName);
        product.setFileName(resultFileName);
        productService.addProduct(product);
        storageService.store(image, resultImageName, name);
        storageService.store(file, resultFileName, name);
        return "redirect:/catalog";
    }

    @GetMapping("/create_product")
    @Override
    public String createProduct(Model model) {
        model.addAttribute("categories", categoryService.findAll());
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
    public String editProduct(@RequestParam String id,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam String price) {
        Product product = new Product(Long.valueOf(id), name, description, new Date());
        product.setPrice(BigDecimal.valueOf(new Double((price))));
        productService.editProduct(product);
        return "redirect:/catalog";
    }

    @GetMapping(value = "/{filename}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody
    byte[] getFile(@PathVariable String filename, @RequestParam("name") String productName) throws IOException {
        return storageService.loadFile(filename, productName);
    }
}
