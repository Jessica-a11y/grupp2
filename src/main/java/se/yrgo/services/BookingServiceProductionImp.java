package se.yrgo.services;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.data.*;
import se.yrgo.domain.*;

@Service("bookingService")
@Transactional
public class BookingServiceProductionImp implements BookingService {
    private BookingDao dao;

    public BookingServiceProductionImp(BookingDao dao) {
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

    @Override
    public void addTable(Table table) {
        dao.createTable(table);
    }

    @Override
    public List<Table> getAllTables() {
        return dao.allTables();
    }

    @Override
    public List<Table> getAllAvailableTables() {
        return dao.availableTables();
    }

    @Override
    @Transactional(rollbackFor = TableNotAvailableException.class)
    public void addReservation(Reservation reservation) throws TableNotAvailableException {
        dao.createReservation(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return dao.allReservations();
    }

    @Override
    public List<Reservation> allReservationsForTable(String tableId) {
        return dao.allReservationsForTable(tableId);
    }

    @Override
    public List<Reservation> allReservationsForCustomer(String customerID) throws CustomerNotFoundException {
        //Before geting the list of reservations, we have to know if the customer excist.
        List<Customer> allCustomers = dao.allCustomers();

        for (Customer customer : allCustomers) {
            //System.out.println(customer.getName() + " " + customer.getCustomerID());
            if(customer.getCustomerID().equals(customerID)){
                return  dao.allReservationsForCustomer(customerID);
            }
        }

        throw new CustomerNotFoundException();
    }

    @Override
    public void updateReservation(Reservation changedReservation) {
        dao.updateReservation(changedReservation);
    }

    

}
