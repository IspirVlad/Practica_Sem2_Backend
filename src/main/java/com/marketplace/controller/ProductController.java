package com.marketplace.controller;

import com.marketplace.dto.*;
import com.marketplace.model.Product;
import com.marketplace.repository.MediaRepository;
import com.marketplace.repository.ProductRepository;
import com.marketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @PostMapping("/createProduct")
    public Long createProduct(@RequestBody ProductDto productDto, OAuth2Authentication auth2Authentication) {
        return productService.createProduct(productDto, auth2Authentication);

    }

    @PostMapping("/addImageToProduct")
    public ResponseEntity<Object> addImageToProduct(@RequestParam("id") Long productid, @RequestParam("file") MultipartFile file) {
        return productService.addImageToProduct(productid,file);
    }

    @GetMapping("/getProduct/{id}")
    public Product getProductById(@PathVariable Long id) throws Exception {
        return productService.getProductById(id);
    }

    @PostMapping("/getProducts")
    public ProductResponseDto getPaginatedProducts(@RequestBody PagingDto pagingDto){
        return productService.getPaginatedProducts(pagingDto);
    }

    @PostMapping("/searchProduct")
    public List<Product> getProducts(@RequestBody SearchBodyDto searchBodyDto){
        return productService.getProducts(searchBodyDto);
    }

}
