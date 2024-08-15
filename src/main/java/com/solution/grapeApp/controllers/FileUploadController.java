package com.solution.grapeApp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.solution.grapeApp.entities.FileDTO;
import com.solution.grapeApp.entities.QueryDTO;
import com.solution.grapeApp.services.FileStorageService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/")
public class FileUploadController {
    private final FileStorageService fileStorageService;

    @PersistenceContext
    private EntityManager entityManager;

    public FileUploadController(FileStorageService service) {
        this.fileStorageService = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<FileDTO>> uploadFiles(@RequestParam List<MultipartFile> files) {
        List<FileDTO> filesObjects = fileStorageService.storesFiles(files);
        return ResponseEntity.ok().body(filesObjects);
    }

    @PostMapping("/generate-query")
    public ResponseEntity<?> postMethodName(@RequestBody QueryDTO queryDTO) {
        try {
            Query query = entityManager.createNativeQuery(queryDTO.getQuery());
            return ResponseEntity.ok().body(query.getResultList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
