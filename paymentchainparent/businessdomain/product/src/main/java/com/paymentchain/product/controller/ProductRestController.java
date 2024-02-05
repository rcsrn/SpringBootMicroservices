package com.paymentchain.product.controller;

import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.product.entities.Product;
import com.paymentchain.product.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/product")
public class ProductRestController {
    
    @Autowired
    ProductRepository repo;

    @GetMapping()
    public List<Product> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductByID(@PathVariable("id") long id) {
        Optional<Product> product = repo.findById(id);
        if (!product.isPresent()) 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    
    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody Product input) {
        Product product = repo.save(input);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
        Optional<Product> product = repo.findById(id);
        if (!product.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        repo.delete(product.get());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Product input) {
        Optional<Product> foundProduct = repo.findById(id);
        if (!foundProduct.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Product product = foundProduct.get();
        product.setCode(input.getCode());
        product.setName(input.getName());
        repo.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    
}
