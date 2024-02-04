package com.paymentchain.customer.controller;

import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.repository.CustomerRepository;

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
@RequestMapping("/customer")
public class CustomerRestController {
    
    @Autowired
    CustomerRepository repo;

    @GetMapping()
    public List<Customer> list() {
        return repo.findAll();
    }

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody Customer input) {
        Customer customer = repo.save(input);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        Optional<Customer> customer = repo.findById(id);
        if (!customer.isPresent()) 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        repo.delete(customer.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PutMapping("path/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable("id") long id, @RequestBody Customer entity) {
        return null;
    }
}
