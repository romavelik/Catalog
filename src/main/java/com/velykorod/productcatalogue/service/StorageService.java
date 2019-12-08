package com.velykorod.productcatalogue.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    public void store(MultipartFile file, String fileName, String productName);

    byte[] loadFile(String fileName, String productName) throws IOException;
}
