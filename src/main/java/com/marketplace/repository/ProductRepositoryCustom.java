package com.marketplace.repository;

import com.marketplace.model.Product;
import com.marketplace.model.User;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> getCustomProduct(String string);

    List<Product> getPaginatedProducts(int firstResults, int maxResults);

    long getTotalProducts();

}
