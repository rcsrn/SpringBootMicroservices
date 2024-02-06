package com.paymentchain.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.entities.CustomerProduct;
import com.paymentchain.customer.repository.CustomerRepository;


@Service
public class SvcCustomerImp implements SvcCustomer{
    @Autowired
    WebClient client;  
    
    @Autowired
    CustomerRepository repo;
    
    @Override
    public Customer getListOfProducts(String code) {
        Customer customer = repo.findByCode(code);
        List<CustomerProduct> products = customer.getProducts();
        products.forEach(x -> {
            String productName = getProductName(x.getId());
            x.setProductName(productName);
        });
        return customer;
    }

    private String getProductName(long id) {
        JsonNode block = client.method(HttpMethod.GET).uri("/" + id)
        .retrieve().bodyToMono(JsonNode.class).block();
        String name = block.get("name").asText();
        return name;
    }
}
