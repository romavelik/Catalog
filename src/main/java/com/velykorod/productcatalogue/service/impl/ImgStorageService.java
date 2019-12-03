package com.velykorod.productcatalogue.service.impl;

import com.velykorod.productcatalogue.exceptions.StorageException;
import com.velykorod.productcatalogue.exceptions.StorageFileNotFoundException;
import com.velykorod.productcatalogue.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImgStorageService implements StorageService {
    @Value("${imagesupload.path}")
    private Path rootLocation;

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public org.springframework.core.io.Resource loadAsResource(String fileName) {
        try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + fileName);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + fileName, e);
        }
    }

    @Override
    public Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }
}
