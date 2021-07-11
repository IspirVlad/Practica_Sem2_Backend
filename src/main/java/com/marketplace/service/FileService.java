package com.marketplace.service;

import com.marketplace.model.Media;
import com.marketplace.model.User;
import com.marketplace.repository.MediaRepository;
import com.marketplace.repository.ProductRepository;
import com.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FileService {
    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserService userService;

    public ResponseEntity<byte[]> getFile(Long id) throws IOException{
        Optional<Media> optionalMedia = mediaRepository.findById(id);

        if (optionalMedia.isPresent()) {
            Media media = optionalMedia.get();
            String path = media.getPath();
            Path pathRoute = Paths.get(path);
            Resource resource = new UrlResource(pathRoute.toUri());

            byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } throw new IllegalArgumentException("No media file: " + id);
    }

    public String addFile(MultipartFile file, String uid){
        String fileName = file.getOriginalFilename();
        Long userid = Long.parseLong(uid);
        String UPLOAD = "C:\\Users\\w\\Desktop\\images\\profile\\";

        try {

            byte[] bytes = file.getBytes();
            Media media = new Media();
            media.setDate(System.currentTimeMillis());
            media.setName(fileName);
            media.setType(file.getContentType());
            String pathName = UPLOAD + file.getOriginalFilename();
            media.setPath(pathName);

            Optional<User> userOptional = userRepository.findById(userid);

            if(userOptional.isPresent())
            {
                mediaRepository.save(media);

                userService.addProfileImage(media,userid);

                Path path = Paths.get(UPLOAD + file.getOriginalFilename());
                Files.write(path, bytes);

                return "file uploaded";
            }

            return "file not uploaded";

        }catch(Exception e){
            e.printStackTrace();
        }

        return "file not uploaded";
    }
}
