package com.backend.cartapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.cartapp.repositories.ProductRepository;
import com.backend.cartapp.models.entities.Product;
import com.backend.cartapp.models.dto.ProductRequest;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findFeatured() {
        return ((List<Product>) repository.findAll())
                .stream()
                .filter(p -> Boolean.TRUE.equals(p.getFeatured()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Product save(Product product){
        return repository.save(product);
    }

    @Override
    @Transactional
    public Product updateFeatured(Long id, boolean featured) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        product.setFeatured(featured);
        return repository.save(product);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        repository.delete(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, ProductRequest request) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());

        if (request.getCategory() != null) {
            product.setCategory(request.getCategory());
        }
        if (request.getFeatured() != null) {
            product.setFeatured(request.getFeatured());
        }

        return repository.save(product);
    }
}