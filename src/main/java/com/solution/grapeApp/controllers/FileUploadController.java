package com.solution.grapeApp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.solution.grapeApp.entities.FileDTO;
import com.solution.grapeApp.services.FileStorageService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("api/")
public class FileUploadController {
    private final FileStorageService fileStorageService;

    public FileUploadController(FileStorageService service) {
        this.fileStorageService = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<FileDTO>> uploadFiles(@RequestParam List<MultipartFile> files) {
        List<FileDTO> filesObjects = fileStorageService.storesFiles(files);
        return ResponseEntity.ok().body(filesObjects);
    }

}
