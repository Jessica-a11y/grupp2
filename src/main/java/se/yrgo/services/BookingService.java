package se.yrgo.services;

import java.util.List;

import se.yrgo.data.CustomerNotFoundException;
import se.yrgo.data.TableNotAvailableException;
import se.yrgo.domain.*;
/**
 * Author Daniel Grahn, Jessica Olofsson
 */
public interface BookingService {

    void addCustomer(Customer customer);
    void addTable(Table table);
    void addReservation(Reservation reservation) throws TableNotAvailableException;
    
    List<Customer> getAllCustomers();
    List<Table> getAllTables();
    List<Table> getAllAvailableTables();
    List<Reservation> getAllReservations();
    
    List<Reservation> allReservationsForTable(String tableId);
    List<Reservation> allReservationsForCustomer(String customerID) throws CustomerNotFoundException;

    //Få fram lediga tider: List<LocalDateTime>

    
   
}