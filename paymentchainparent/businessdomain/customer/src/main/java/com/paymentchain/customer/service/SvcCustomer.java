package com.paymentchain.customer.service;

import com.paymentchain.customer.entities.Customer;

public interface SvcCustomer {
    
    Customer getListOfProducts(String code);
}
