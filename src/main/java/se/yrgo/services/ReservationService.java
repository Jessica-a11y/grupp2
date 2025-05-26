package se.yrgo.services;

import java.util.List;

import se.yrgo.data.CustomerNotFoundException;
import se.yrgo.data.TableNotAvailableException;
import se.yrgo.domain.Reservation;

public interface ReservationService {
    void addReservation(Reservation reservation) throws TableNotAvailableException;
    List<Reservation> getAllReservations();
    List<Reservation> allReservationsForTable(String tableId);
    List<Reservation> allReservationsForCustomer(String reservationId);
} 
