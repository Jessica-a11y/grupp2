package se.yrgo.services;

import java.util.List;

<<<<<<< HEAD
=======
//import se.yrgo.data.CustomerNotFoundException;
>>>>>>> javadoc
import se.yrgo.data.TableNotAvailableException;
import se.yrgo.domain.Reservation;

/**
 * ReservationService provides operations for managing reservations.
 * 
 * <p>
 * This interface allows adding, retrieving, updating and removing reservations,
 * as well as retrieving reervations by table or customer.
 * </p>
 * 
 * @author Daniel Grahn, Jessica Olofsson
 */

public interface ReservationService {

    /**
     * Adds a new reservation to the system.
     * 
     * @param reservation The {@link Reservation} to add
     * @throws TableNotAvaliableException if no suitable table is avaliable for the reservation
     */
    void addReservation(Reservation reservation) throws TableNotAvailableException;

    /**
     * Retrieves a reservation by its unique ID.
     * 
     * @param reservationId The unique identifies of the reservation
     * @return the {@link Reservation} if found, otherwise {@code null}
     */
    public Reservation getReservation(String reservatinId);

    /**
     * Removes a reservation with the specified ID.
     * 
     * @param reservationID The unique identifies of the reservation to remove
     */
    public void removeReservation(String reservationId);

    /**
     * Updates an existing reservation with new information.
     * 
     * @param updateReservation The {@link Reservation} object containing updated information
     */
    public void changeReservation(Reservation updatedReservation);

    /**
     * Retrieves a list of all reservations in the system.
     * 
     * @return a {@link List} of all {@link Reservation} objects
     */
    List<Reservation> getAllReservations();

    /**
     * Retrieves all reservations for aspecific table.
     * 
     * @param tableId The unique identifies of the table
     * @return a {@link List} of {@link Reservation} objects for the specific table
     */
    List<Reservation> allReservationsForTable(String tableId);

    /**
     * Retrieves all reservations for a specific customer.
     * 
     * @param reservationId The unique identifier of the customer
     * @return a {@link List} of {2link Reservation} objects for spe specified customer
     */
    List<Reservation> allReservationsForCustomer(String reservationId);
} 
