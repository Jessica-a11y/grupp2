package se.yrgo.services;

import java.util.List;

import se.yrgo.data.CustomerNotFoundException;
import se.yrgo.data.TableNotAvailableException;
import se.yrgo.domain.Reservation;

public interface ReservationService {
    void addReservation(Reservation reservation) throws TableNotAvailableException;
    public Reservation getReservation(String reservatinId);
    public void removeReservation(String reservationId);
    public void changeReservation(Reservation updatedReservation);
    List<Reservation> getAllReservations();
    List<Reservation> allReservationsForTable(String tableId);
    List<Reservation> allReservationsForCustomer(String reservationId);
} 
