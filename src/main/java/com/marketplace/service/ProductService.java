package com.marketplace.service;

import com.marketplace.dto.PagingDto;
import com.marketplace.dto.ProductDto;
import com.marketplace.dto.ProductResponseDto;

import com.marketplace.dto.*;
import com.marketplace.model.Media;
import com.marketplace.model.Product;
import com.marketplace.model.User;
import com.marketplace.repository.MediaRepository;
import com.marketplace.repository.ProductRepository;
import com.marketplace.repository.ProductRepositoryCustom;
import com.marketplace.repository.UserRepository;
import com.marketplace.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MediaRepository mediaRepository;

    public Long createProduct(ProductDto productDto, OAuth2Authentication auth2Authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) auth2Authentication.getPrincipal();
        String email = userPrinciple.getUsername();
        Optional<User> user = userRepository.findByEmail(email);

        Product product = new Product(productDto);
        product.setUser(user.get());
        productRepository.save(product);

        return product.getId();
    }

    public ResponseEntity<Object> addImageToProduct(Long productId, Media productImage) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setproductImage(productImage);

            productRepository.save(product);

            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

//    public ResponseEntity<Object> getProductById(Long id) {
//        Optional<Product> optionalProduct = productRepository.findById(id);
//
//        if (optionalProduct.isPresent()) {
//            Product product = optionalProduct.get();
//
//            return new ResponseEntity<>(product, HttpStatus.OK);
//        }
//        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
//    }

    public Product getProductById(Long id) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            return product;
        }throw new Exception("Product not found");
    }

    public ResponseEntity<Object> addImageToProduct(Long productId, MultipartFile file){
        String fileName = file.getOriginalFilename();
        String UPLOAD = "C:\\Users\\w\\Desktop\\images\\products\\";

        try {

            byte[] bytes = file.getBytes();
            Media media = new Media();
            media.setDate(System.currentTimeMillis());
            media.setName(fileName);
            media.setType(file.getContentType());
            String pathName = UPLOAD + file.getOriginalFilename();
            media.setPath(pathName);

            mediaRepository.save(media);
            Path path = Paths.get(UPLOAD + file.getOriginalFilename());
            Files.write(path, bytes);

            return productService.addImageToProduct(productId,media);

        }catch(Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>("Image not added", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public ProductResponseDto getPaginatedProducts(PagingDto pagingDto){
        int page = pagingDto.getPage();
        int size = pagingDto.getSize();

        int firstResults = page * size;
        int maxResults = firstResults + size;

        List<Product> products = productRepository.getPaginatedProducts(firstResults,maxResults);
        long totalProducts = productRepository.getTotalProducts();

        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setProducts(products);
        responseDto.setFrom(firstResults);
        responseDto.setTo(maxResults);
        responseDto.setTotalProducts(totalProducts);

        return responseDto;
    }

    public List<Product> getProducts(SearchBodyDto searchBodyDto){
        List<Product> productList = productRepository.getCustomProduct(searchBodyDto.getName());

        return productList;
    }

}
