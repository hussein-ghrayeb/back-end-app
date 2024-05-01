package com.solution.grapeApp.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.solution.grapeApp.entities.FileDTO;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    public FileStorageService(Environment env) {
        this.fileStorageLocation = Paths.get(env.getProperty("app.file.upload-dir"), "/opt/uploads/files")
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    private String getFileExtention(String fileName) {
        if (fileName == null)
            return null;
        String[] fileNameParts = fileName.split("\\.");
        return fileNameParts[fileNameParts.length - 1];
    }

    public List<FileDTO> storesFiles(List<MultipartFile> files) {
        List<FileDTO> filesObjects = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            String fileName = file.getOriginalFilename();

            try {
                if (fileName.contains("..")) {
                    throw new RuntimeException("Sorry! FileName contains invalid path sequence " + fileName);
                }

                Path targetLocation = this.fileStorageLocation.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                FileDTO object = new FileDTO();
                object.setFile(fileName);
                object.setExtension(getFileExtention(fileName));
                object.setFileName(fileName);

                filesObjects.add(object);

            } catch (IOException ex) {
                throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
            }
        }

        return filesObjects;
    }
}
