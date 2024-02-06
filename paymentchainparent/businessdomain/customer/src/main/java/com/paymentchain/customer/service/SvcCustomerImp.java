package com.paymentchain.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.paymentchain.customer.entities.Customer;

@Service
public class SvcCustomerImp implements SvcCustomer{
    @Autowired
    WebClient client;   
    
    private String getProductName(long id) {
        JsonNode block = client.method(HttpMethod.GET).uri("/" + id)
        .retrieve().bodyToMono(JsonNode.class).block();
        String name = block.get("name").asText();
        return name;
    }
}
