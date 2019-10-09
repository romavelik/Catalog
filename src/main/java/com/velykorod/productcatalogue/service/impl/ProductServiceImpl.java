package com.velykorod.productcatalogue.service.impl;

import com.velykorod.productcatalogue.persistance.domain.impl.Product;
import com.velykorod.productcatalogue.persistance.repository.ProductRepository;
import com.velykorod.productcatalogue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Cacheable(value = "products")
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Cacheable(value = "products")
    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @CachePut(value= "products", key = "#product.name")
    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @CacheEvict(value = "products", key = "#id")
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @CachePut(value = "products", key = "#product.name")
    @Override
    public void editProduct(Product product) {
        productRepository.updateProduct(product.getName(), product.getDescription(), product.getId());
    }

    @Cacheable(value = "products", key = "#id")
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }
}
