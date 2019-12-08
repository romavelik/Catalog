package com.velykorod.productcatalogue.service.impl;

import com.velykorod.productcatalogue.exceptions.StorageException;
import com.velykorod.productcatalogue.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class StorageServiceImpl implements StorageService {
    @Value("${upload.path}")
    private String rootLocation;

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public void store(MultipartFile file, String fileName, String productName) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            File directory = new File(rootLocation,productName);
            directory.mkdir();
            File stored = new File(directory, fileName);
            file.transferTo(stored);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }


    public byte[] loadFile(String fileName, String productName) throws IOException {
        File file = new File(rootLocation.concat("/").concat(productName), fileName);
        if(!file.exists()) {
            throw new StorageException("Failed to load the file" + fileName);
        }
        return Files.readAllBytes(file.toPath());
    }
}
