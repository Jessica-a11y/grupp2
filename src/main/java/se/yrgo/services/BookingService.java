package se.yrgo.services;

import java.util.List;

import se.yrgo.data.CustomerNotFoundException;
import se.yrgo.data.TableNotAvailableException;
import se.yrgo.domain.*;
/**
 * Author Daniel Grahn, Jessica Olofsson
 */
public interface BookingService {
    public void makeReservation();
    public void updateReservation(Reservation changedReservation);
    public void deleteReservatuion();
    public void findReservation();
    public void availableTables();

    //Få fram lediga tider: List<LocalDateTime>

    
   
}