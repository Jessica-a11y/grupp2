package se.yrgo.services;

import java.util.List;

import se.yrgo.domain.Customer;

public interface CustomerService {
    void addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    
} 
