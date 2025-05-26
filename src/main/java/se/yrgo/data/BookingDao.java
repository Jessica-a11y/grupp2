package se.yrgo.data;

import se.yrgo.domain.*;
import java.util.*;

public interface BookingDao {
    //Creat
    public void createReservation(Reservation newReservation);
    public void createCustomer(Customer newCustomer);
    public void createTable(DiningTable newTable);

    //get by id
    public DiningTable findTableById(String tableId);
    public Customer findCustomer(String customerId);
    public Reservation findReservation(String reservationId);
    
    //get all
    public List<Customer> allCustomers();
    public List<DiningTable> allTables();
    public List<Reservation> allReservations();
    public List<DiningTable> availableTables();
    public List<Reservation> allReservationsForTable(String tableId);
    public List<Reservation> allReservationsForCustomer(String reservationId); 

    //Update
    public void updateReservation(Reservation changedReservation);
    public void updateCustomer(Customer changedCustomer);
    public void updateTable(DiningTable changedTable);
    public void changeAvailability(String tableNumber);

    //Delet
    public void deletReservation(String reservationId);
    public void deletCustomer(Customer customer);
    public void deletTable(DiningTable table);
}