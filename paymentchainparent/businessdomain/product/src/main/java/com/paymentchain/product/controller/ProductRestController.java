package com.paymentchain.product.controller;

import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.product.entities.Product;
import com.paymentchain.product.repository.ProductRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/product")
public class ProductRestController {
    
    @Autowired
    ProductRepository repo;

    @GetMapping()
    public List<Product> findAll() {
        return repo.findAll();
    }

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody Product input) {
        Product product = repo.save(input);
        return ResponseEntity.ok(product);
    }
    
}
