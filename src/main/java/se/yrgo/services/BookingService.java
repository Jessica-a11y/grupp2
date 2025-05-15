package se.yrgo.services;

import java.util.List;

import se.yrgo.domain.*;

public interface BookingService {

    void addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    void addTable(Table table);
    List<Table> getAllTables();
    void addReservation(Reservation reservation);
    List<Reservation> getAllReservations();

    //Vad skulle vi vilja gör 
 
    //få alla lediga bord: List<Table>
    //Få fram lediga tider: List<LocalDateTime>

    //få alla bokade bord från en viss person: List<Table>
    //få alla reservationer för ett vist bord List<Reservation>
}