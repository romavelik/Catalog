package com.velykorod.productcatalogue.service;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

public interface StorageService {
    public void store(MultipartFile file);

    public Resource loadAsResource(String fileName);
}
