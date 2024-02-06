package com.paymentchain.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.paymentchain.customer.entities.Customer;

@Service
public class SvcCustomerImp implements SvcCustomer{
    @Autowired
    WebClient client;   
    
    @Override
    public Customer getProductNames(long id) {
        return null;
    }
}
