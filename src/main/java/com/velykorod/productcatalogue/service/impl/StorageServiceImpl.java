package com.velykorod.productcatalogue.service.impl;

import com.velykorod.productcatalogue.exceptions.StorageException;
import com.velykorod.productcatalogue.persistance.domain.impl.AudioTrack;
import com.velykorod.productcatalogue.persistance.domain.impl.Product;
import com.velykorod.productcatalogue.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class StorageServiceImpl implements StorageService {
    @Value("${upload.path}")
    private String rootLocation;

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

    @Override
    public void delete(String productName) {
        try {
            FileSystemUtils.deleteRecursively(Paths.get(rootLocation, productName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDirName(String oldName, String newName) {
        File oldDir = new File(rootLocation, oldName);
        File newDir = new File(rootLocation, newName);
        oldDir.renameTo(newDir);
    }

    @Override
    public byte[] loadAsZip(String directory) throws IOException {
        String zipName = directory.concat("_package.zip");
        File fileToZip = new File(rootLocation + "/" + directory);
        File[] innerFiles = fileToZip.listFiles();
        FileOutputStream fos = new FileOutputStream(rootLocation + "/" + zipName);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for(File sourceFile : innerFiles){
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            zipOut.putNextEntry(new ZipEntry(sourceFile.getName())); byte[] bytes = new byte[1024];
            int length;
            while((length = fileInputStream.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fileInputStream.close();
        }
        zipOut.close();
        fos.close();
        return loadFile("", zipName);
    }

}
