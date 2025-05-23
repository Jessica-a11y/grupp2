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
    List<Customer> getAllCustomers();
    void addTable(Table table);
    List<Table> getAllTables();
    List<Table> getAllAvailableTables();
    void addReservation(Reservation reservation) throws TableNotAvailableException;
    List<Reservation> getAllReservations();
    List<Reservation> allReservationsForTable(String tableId);
    List<Reservation> allReservationsForCustomer(String customerID) throws CustomerNotFoundException;

    //Få fram lediga tider: List<LocalDateTime>

    
   
}