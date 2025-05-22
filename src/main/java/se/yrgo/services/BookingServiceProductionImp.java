package se.yrgo.services;

import java.util.*;

import org.springframework.stereotype.Service;

import se.yrgo.data.BookingDao;
import se.yrgo.domain.*;

@Service("bookingService")
public class BookingServiceProductionImp implements BookingService{
    private BookingDao dao;

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
    public void addReservation(Reservation reservation) {
        dao.createReservation(reservation); 
    }

    @Override
    public List<Reservation> getAllReservations() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllReservations'");
    }

    @Override
    public List<Reservation> allReservationsForTable(Table table) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'allReservationsForTable'");
    }

    @Override
    public List<Reservation> allReservationsForCustomer(String customerID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'allReservationsForCustomer'");
    }
    
}
