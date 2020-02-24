package com.bharath.spring;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bharath.spring.entity.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CustomerdataApplicationTests {

	private static final Long ID = 2L;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setName("John");
		customer.setEmail("john.doe.x2t5@nomail.org");
		
		customerRepository.save(customer);
	}
	
	@Test
	public void testReadCustomer() {
		Optional<Customer> customerOpt = customerRepository.findById(ID);
		assertTrue(customerOpt.isPresent());
	}
	
	@Test
	public void testUpdateCustomer() {
		// try to retrieve Customer from repository by their ID
		Optional<Customer> customerOpt = customerRepository.findById(ID);
		assertTrue(customerOpt.isPresent());
		// unwrap the Customer from the Optional
		Customer customer = customerOpt.get();		
		// change thri email
		customer.setEmail("jdoe1949@veterans-united.info");
		// write back Customer instance to repository
		customerRepository.save(customer);
		
		// retrieve Customer again from repository
		customer = customerRepository.findById(ID).get();
		// make sure it's indeed JOHN
		assertEquals("JOHN", customer.getName().toUpperCase());
		// verify that the email address has changed
		assertEquals("jdoe1949@veterans-united.info", customer.getEmail());		
	}
	
	@Test
	public void testDeleteCustomer() {
		// create a new Customer instance from scratch
		Customer customer = new Customer();
		// set their ID (the table's primary key)
		customer.setId(ID);
		// delete the Customer from the Reposuitory by object reference
		customerRepository.delete(customer);
		
		// make sure the table is empty again
		Iterable<Customer> customers = customerRepository.findAll();
		assertFalse(customers.iterator().hasNext());
	}
}
