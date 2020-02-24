package com.bharath.spring;

import org.springframework.data.repository.CrudRepository;

import com.bharath.spring.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> { }
