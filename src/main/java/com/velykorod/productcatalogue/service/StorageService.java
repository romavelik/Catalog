package com.velykorod.productcatalogue.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {
    public void store(MultipartFile file);

    public Resource loadAsResource(String fileName);

    public Path load(String fileName);
}
