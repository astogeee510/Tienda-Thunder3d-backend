package com.backend.cartapp.services;

import java.util.List;

import com.backend.cartapp.models.dto.ProductRequest;
import com.backend.cartapp.models.entities.Product;

public interface ProductService {

    List<Product> findAll();

    List<Product> findFeatured();

    Product save(Product product);

    Product updateFeatured(Long id, boolean featured);

    void delete(Long id);

    Product updateProduct(Long id, ProductRequest request);
}