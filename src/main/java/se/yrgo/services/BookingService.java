package se.yrgo.services;

import se.yrgo.data.ReservationNotAvailable;
import se.yrgo.data.TableNotAvailableException;
import se.yrgo.domain.*;
/**
 * Author Daniel Grahn, Jessica Olofsson
 */
public interface BookingService {
    void makeReservation(String date, String time, int amountOfSeats, String fullName, String email, String number) throws TableNotAvailableException, ReservationNotAvailable;
    void updateReservation(String reservationId, String date, String time);
    void deleteReservatuion(String reservationId);
    Reservation findReservation(String reservationID);
}