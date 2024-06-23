package com.manganet.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    private static final String FILE_DIRECTORY = "/home/obras";
    private final Path rootLocation = Paths.get(FILE_DIRECTORY);

    public String storeFile(MultipartFile file, String ubicacion) throws IOException {
        Path directoryPath = Paths.get(FILE_DIRECTORY + "/" + ubicacion);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
        Path filePath = directoryPath.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        
        return filePath.toString();
    }

    public void storeMultipleFiles(MultipartFile[] files, String ubicacion) throws IOException {
        Path directoryPath = Paths.get(FILE_DIRECTORY + "/" + ubicacion);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
        for (MultipartFile file : files) {
            Path filePath = directoryPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public Resource loadFile(String filename, String ubicacion) {
        try {
            Path file = rootLocation.resolve(ubicacion + "/" + filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("File not found or not readable!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error loading file!", e);
        }
    }

    public List<Resource> loadFiles(String[] filenames, String ubicacion) {
        List<Resource> resources = new ArrayList<>();
        for (String filename : filenames) {
            try {
                Path file = rootLocation.resolve(ubicacion + "/" + filename);
                Resource resource = new UrlResource(file.toUri());
                if (resource.exists() || resource.isReadable()) {
                    resources.add(resource);
                } else {
                    throw new RuntimeException("File not found or not readable: " + filename);
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error loading file: " + filename, e);
            }
        }
        return resources;
    }

}
