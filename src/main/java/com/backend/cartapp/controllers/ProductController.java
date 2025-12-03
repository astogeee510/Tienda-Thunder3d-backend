package com.backend.cartapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import com.backend.cartapp.services.ProductService;
import com.backend.cartapp.models.entities.Product;
import com.backend.cartapp.models.dto.ProductRequest;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8081"})
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public List<Product> list() {
        return service.findAll();
    }

    @GetMapping("/products/featured")
    public List<Product> listFeatured() {
        return service.findFeatured();
    }

    @PostMapping("/products")
    public ResponseEntity<?> create(@RequestBody ProductRequest request){
        try{
            Product p = new Product();
            p.setName(request.getName());
            p.setDescription(request.getDescription());
            p.setPrice(request.getPrice());
            p.setCategory(request.getCategory());
            Boolean featured = request.getFeatured() != null ? request.getFeatured() : Boolean.FALSE;
            p.setFeatured(featured);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.save(p));
        }catch(Exception e){
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/products/{id}/featured")
    public ResponseEntity<?> toggleFeatured(@PathVariable Long id, @RequestParam boolean featured) {
        try {
            Product updated = service.updateFeatured(id, featured);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductRequest request) {
        try {
            Product updated = service.updateProduct(id, request);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}