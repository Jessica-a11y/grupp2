package se.yrgo.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.data.BookingDao;
import se.yrgo.domain.Customer;

/**
 * Production implementation of the {@link CustomerService} interface.
 * 
 * <p>
 * This service provides methods to manage customer by delegating operations to the underlying {@link BookingDao}.
 * </p>
 * <p>
 * Annotated as a Spring {@code @Service} ad amrked as {@code @Transactional} to ensure transactional integrity.
 * </p>
 * 
 * @author Daniel Grahn, Jessica Olofsson, for JavaDoc: Emilia Jarleback
 */

@Service("customerService")
@Transactional
public class CustomerServiceProductionImpl implements CustomerService{
    private BookingDao dao;

    /**
     * Constructs a new CustomerServiceProductionImpl with the specified BookingDao.
     * 
     * @param dao The {@link BookingDao} is used for customerpersistence operations
     */
    public CustomerServiceProductionImpl(BookingDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Adds a new customer by delegating to the DAO layer.
     * </p>
     * 
     * @param customer The {@link Customer} to add
     */
     @Override
    public void addCustomer(Customer customer) {
        dao.createCustomer(customer);
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Retrieves all customers from the DAO layer.
     * </p>
     * 
     * @param a list of all {@link Customer} objects
     */
    @Override
    public List<Customer> getAllCustomers() {
        return dao.allCustomers();
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Retrieves a customer by their unique ID from the DAO layer.
     * </p>
     * 
     * @param customerID The unique identifier of the customer
     * @return the {@link Customer} object if found, otherwise {@code null}
     */
    @Override
    public Customer getCustomer(String customerID) {
        return dao.findCustomer(customerID);
    }
}
