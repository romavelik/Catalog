package com.velykorod.productcatalogue.service;

import com.velykorod.productcatalogue.persistance.domain.impl.AudioTrack;
import com.velykorod.productcatalogue.persistance.domain.impl.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageService {
    void store(MultipartFile file, String fileName, String productName);

    byte[] loadFile(String fileName, String productName) throws IOException;

    List <AudioTrack> storeMultipleFiles(MultipartFile [] files, Product product);

    void delete(String productName);

    void updateDirName(String oldName, String newName);

    byte[] loadAsZip(String directory) throws IOException;

}
