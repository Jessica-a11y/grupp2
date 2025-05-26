package se.yrgo.data;

import se.yrgo.domain.*;
import java.util.*;

public interface BookingDao {
    //Creat
    public void createReservation(Reservation newReservation);
    public void createCustomer(Customer newCustomer);
    public void createTable(Table newTable);

    //get by id
    public Table findTableById(String tableId);
    public Customer findCustomer(String customerId);
    
    //get all
    public List<Customer> allCustomers();
    public List<Table> allTables();
    public List<Reservation> allReservations();
    public List<Table> availableTables();
    public List<Reservation> allReservationsForTable(String tableId);
    public List<Reservation> allReservationsForCustomer(String reservationId); 

    //Update
    public void updateReservation(Reservation changedReservation);
    public void updateCustomer(Customer changedCustomer);
    public void updateTable(Table changedTable);

    //Delet
    public void deletReservation(Reservation reservation);
    public void deletCustomer(Customer customer);
    public void deletTable(Table table);
}