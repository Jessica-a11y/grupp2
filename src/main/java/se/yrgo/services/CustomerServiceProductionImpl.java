package se.yrgo.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.data.BookingDao;
import se.yrgo.domain.Customer;

@Service("customerService")
@Transactional
public class CustomerServiceProductionImpl implements CustomerService{
    private BookingDao dao;

    public CustomerServiceProductionImpl(BookingDao dao) {
        this.dao = dao;
    }

     @Override
    public void addCustomer(Customer customer) {
        dao.createCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return dao.allCustomers();
    }
}
