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
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/customer")
public class CustomerRestController {
    
    @Autowired
    CustomerRepository repo;

    @GetMapping()
    public List<Customer> list() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") long id) {
        Optional<Customer> customer = repo.findById(id);
        if (!customer.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        return new ResponseEntity<>(customer, HttpStatus.OK);      
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
    
    @PutMapping("/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable("id") long id, @RequestBody Customer input) {
        Optional<Customer> foundCustomer = repo.findById(id);
        if (!foundCustomer.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
        Customer customer = foundCustomer.get();
        customer.setName(input.getName());
        customer.setAddress(input.getAddress());
        customer.setCode(input.getCode());
        customer.setIban(input.getIban());
        customer.setPhone(input.getPhone());
        customer.setSurname(input.getSurname());

        repo.save(customer);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
