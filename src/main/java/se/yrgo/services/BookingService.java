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
    
    //Get all reservations for a certain table: List<Reservation>
    List<Reservation> allReservationsForTable(Table table);
    //Get all reservations for a certain customer: List<Table>
    List<Reservation> allReservationsForCustomer(String customerID);

    //Få fram lediga tider: List<LocalDateTime>

    
   
}