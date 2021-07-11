package com.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marketplace.dto.ProductDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private int inventory;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="productImage_id", referencedColumnName="id")
    private Media productImage;

    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

    public Product(ProductDto productDto) {
        this.name = productDto.getName();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
    }

    public Product(ProductDto productDto, Media productImage) {
        this.name = productDto.getName();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.productImage = productImage;
    }

    public Product(String name, String description, Double price, int inventory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
    }


    public void setProductImage(Media productImage) {
        this.productImage = productImage;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public Media getProductImage() {
        return productImage;
    }

    public void setproductImage(Media productImage) {
        this.productImage = productImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
