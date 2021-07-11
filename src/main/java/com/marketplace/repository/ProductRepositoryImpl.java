package com.marketplace.repository;

import com.marketplace.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Product> getCustomProduct(String productName) {
        return entityManager.createQuery("select p from Product p where p.name = :name ", Product.class)
                .setParameter("name", productName)
                .getResultList();
    }

    @Override
    public List<Product> getPaginatedProducts(int firstResults, int maxResults) {
        String query = "select p from Product p";
        List<Product> products = entityManager.createQuery(query, Product.class)
                .setFirstResult(firstResults)
                .setMaxResults(maxResults)
                .getResultList();
        return products;
    }

    @Override
    public long getTotalProducts() {
        String query = "select count(p) from Product p";

        long totalProducts = entityManager.createQuery(query,Long.class)
                .getSingleResult();
        return totalProducts;
    }
}
