package com.marketplace.dto;

import com.marketplace.model.Product;

import java.util.List;

public class ProductResponseDto {
    private List<Product> products;
    private long totalProducts;
    private int from;
    private int to;

    public ProductResponseDto() {
    }

    public ProductResponseDto(List<Product> products, long totalProducts, int from, int to) {
        this.products = products;
        this.totalProducts = totalProducts;
        this.from = from;
        this.to = to;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
