package com.velykorod.productcatalogue.service.impl;

import com.velykorod.productcatalogue.persistance.domain.impl.Product;
import com.velykorod.productcatalogue.persistance.repository.ProductRepository;
import com.velykorod.productcatalogue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void editProduct(Product product) {
        productRepository.updateProduct(product.getName(), product.getDescription(), product.getId(), product.getPrice());
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }
}
