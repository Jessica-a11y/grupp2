package se.yrgo.data;

import se.yrgo.domain.*;
import java.util.*;

public interface BookingDao {
    //Creat
    public void createReservation(Reservation newReservation);
    public void createCustomer(Customer newCustomer);
    public void createTable(Table newTable);
    //get all
    public List<Customer> allCustomers();
    public List<Table> allTables();
    public List<Reservation> allReservations();
    public List<Table> availableTables();
    public List<Reservation> allReservationsForTable(String tableId);
    public List<Reservation> allReservationsForCustomer(String customerID); 
}