package com.paymentchain.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paymentchain.customer.entities.Customer;

import jakarta.transaction.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE Customer SET name = :name WHERE id = :id", nativeQuery = true)
    void updateCustomerById(@Param("id") long id, @Param("name") String name);
}
