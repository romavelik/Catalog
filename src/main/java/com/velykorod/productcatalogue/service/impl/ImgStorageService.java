package com.velykorod.productcatalogue.service.impl;

import com.velykorod.productcatalogue.service.StorageService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

public class ImgStorageService implements StorageService {
    @Override
    public void store(MultipartFile file) {

    }

    @Override
    public Resource loadAsResource(String fileName) {
        return null;
    }
}
