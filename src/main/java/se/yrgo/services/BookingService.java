package se.yrgo.services;

import java.util.List;

import se.yrgo.domain.*;
/**
 * Author Daniel Grahn, Jessica Olofsson
 */
public interface BookingService {

    void addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    void addTable(Table table);
    List<Table> getAllTables();
    List<Table> getAllAvailableTables();
    void addReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    List<Reservation> allReservationsForTable(String tableId);
    List<Reservation> allReservationsForCustomer(String customerID);

    //Få fram lediga tider: List<LocalDateTime>

    
   
}