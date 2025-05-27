package se.yrgo.services;

import java.util.List;

import se.yrgo.domain.Customer;

/**
 * CustomerService provides operations for managing customer data.
 * 
 * <p>
 * This interface allows adding new customers, retrieving all customers
 * and fetching a specific customer by their unique ID.
 * </p>
 * 
 * @author Daniel Grahn, Jessica Olofsson, for JavaDoc: Emilia Jarleback
 */

public interface CustomerService {

    /**
     * Adds a new customer to the system.
     * 
     * @param customer The {@link Customer} object to add
     */
    public void addCustomer(Customer customer);

    /**
     * Retrieves a list of all customer
     * 
     * @return a {@link List} of all {@link Customer} objects
     */
    public List<Customer> getAllCustomers();

    /**
     * Retrieves a customer by their unique ID.
     * 
     * @param customerID The unique identifies of the customer
     * @return the {@link Customer} object of found, otherwise {@code null}
     */
    public Customer getCustomer(String customerID); 
} 
