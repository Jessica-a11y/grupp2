package se.yrgo.services;

import se.yrgo.data.ReservationNotAvailable;
import se.yrgo.data.TableNotAvailableException;
import se.yrgo.domain.*;

/**
 * BookingService manages restaurant table reservations.
 * 
 * <p>
 * This interface provides methods for creating, updating, deleting and
 * searching for reservations,
 * as well as displaying avaliable tables.
 * </p>
 * 
 * @author Daniel Grahn, Jessica Olofsson, for JavaDoc: Emilia Jarleback
 */

public interface BookingService {

    /**
     * Creates a new table reservation.
     * 
     * @param date          The date of the reservation (format: "yyyy-MM-dd")
     * @param time          The time of the reservation (format: "HH:mm")
     * @param amountOfSeats The number of seats to reserve
     * @param fullName      The customer's full name
     * @param email         The customer's email address
     * @param number        The customer's phone number
     * @throws TableNotAvaliableException if no table is avaliable for the specifies
     *                                    date, time and number of seats
     * @throws ReservationNotAvaliable    if the reservation cannot be made for
     *                                    other reasons
     */
    public void makeReservation(String date, String time, int amountOfSeats, String fullName, String email,
            String number) throws TableNotAvailableException, ReservationNotAvailable;

    /**
     * Updates the information of an existing reservation.
     * 
     * @param changedReservation The reservation object containing the updated
     *                           information
     */
    public void updateReservation(Reservation changedReservation);

    /**
     * Deletes a reservation with the specified ID.
     * 
     * @param reservationId the ID of the reservation to delete
     */
    public void deleteReservatuion(String reservationId);

    /**
     * Finds a reservation by its ID.
     * 
     * @param reservationID the ID of the reservation to retrieve
     * @return the reservation object if found, otherwise null
     */
    public Reservation findReservation(String reservationID);

    /**
     * Displays avaliable tables for booking.
     * 
     * <p>
     * The specific implementation and return type should be defined in the
     * implementing class.
     * </p>
     */
    public void availableTables();

    // Få fram lediga tider: List<LocalDateTime>
}