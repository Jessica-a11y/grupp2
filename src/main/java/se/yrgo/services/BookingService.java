package se.yrgo.services;

import java.util.List;

import se.yrgo.data.CustomerNotFoundException;
import se.yrgo.data.TableNotAvailableException;
import se.yrgo.domain.*;
/**
 * Author Daniel Grahn, Jessica Olofsson
 */
public interface BookingService {
    public void makeReservation(Customer customer);
    public void changeReservation();
    public void deleteReservatuion();
    public void findReservation();
    public void availableTables();


    void updateReservation(Reservation changedReservation);

    //Få fram lediga tider: List<LocalDateTime>

    
   
}