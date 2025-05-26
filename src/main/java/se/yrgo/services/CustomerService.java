package se.yrgo.services;

import java.util.List;

import se.yrgo.domain.Customer;

public interface CustomerService {
    public void addCustomer(Customer customer);
    public List<Customer> getAllCustomers();
    public Customer getCustomer(String customerID); 
} 
