package com.marketplace.controller;

import com.marketplace.model.Media;
import com.marketplace.model.Product;
import com.marketplace.model.User;
import com.marketplace.repository.MediaRepository;
import com.marketplace.repository.ProductRepository;
import com.marketplace.repository.UserRepository;
import com.marketplace.service.FileService;
import com.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
public class FileController {

    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserService userService;

    @Autowired
    private FileService fileService;

    @GetMapping("/getImage/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) throws IOException {
        return fileService.getFile(id);
    }

    @PostMapping("/addProfilePic/{uid}") String addFile(@RequestParam("file") MultipartFile file, @PathVariable String uid) {
        return fileService.addFile(file,uid);
    }
}