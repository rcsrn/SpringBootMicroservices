package com.paymentchain.customer.controller;

import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.repository.CustomerRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/customer")
public class CustomerRestController {
    
    @Autowired
    CustomerRepository repo;

    @GetMapping()
    public List<Customer> findAll() {
        return repo.findAll();
    }

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody Customer input) {
        Customer customer = repo.save(input);
        return ResponseEntity.ok(customer);
    }
    
}
