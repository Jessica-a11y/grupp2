package se.yrgo.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import se.yrgo.data.CustomerNotFoundException;
import se.yrgo.data.TableNotAvailableException;
import se.yrgo.domain.*;
/**
 * Author Daniel Grahn, Jessica Olofsson
 */
public interface BookingService {
    public void makeReservation(String customerId, LocalDate lD, LocalTime lT);
    public void updateReservation(Reservation changedReservation);
    public void deleteReservatuion(String reservationId);
    public Reservation findReservation(String reservationID);
    public void availableTables();

    //Få fram lediga tider: List<LocalDateTime>

    
   
}