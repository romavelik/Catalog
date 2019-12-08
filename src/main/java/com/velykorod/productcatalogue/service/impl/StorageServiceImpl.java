package com.velykorod.productcatalogue.service.impl;

import com.velykorod.productcatalogue.exceptions.StorageException;
import com.velykorod.productcatalogue.persistance.domain.MediaFile;
import com.velykorod.productcatalogue.persistance.domain.impl.AudioTrack;
import com.velykorod.productcatalogue.persistance.domain.impl.Product;
import com.velykorod.productcatalogue.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {
    @Value("${upload.path}")
    private String rootLocation;

//    @Autowired
//    ResourceLoader resourceLoader;
    @Autowired
    private AudioTrackServiceImpl audioTrackService;

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

    @Override
    public List<AudioTrack> storeMultipleFiles(MultipartFile[] files, Product product) {
        List <AudioTrack> mediaFiles = new ArrayList<>();
        for (MultipartFile file : files){
            String fileName = file.getOriginalFilename();
            store(file, fileName, product.getName());
            AudioTrack mediaFile = new AudioTrack(fileName, product);
            mediaFiles.add(mediaFile);
        }
        audioTrackService.addTrackList(mediaFiles);
        return mediaFiles;
    }
}
