package se.yrgo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.data.BookingDao;

import se.yrgo.data.TableNotAvailableException;

import se.yrgo.domain.Reservation;

/**
 * Production implementation of the {2link ReservationService} interface.
 * 
 * <p>
 * This service coordinates reservation management by delegating operations to
 * the underlying {@link BookingDao}.
 * </p>
 * 
 * <p>
 * Annotated as a Spring {@code @Service} and amrked as {@code @transactional}
 * to ensure transactional integrity.
 * </p>
 * 
 * @author Daniel Grahn, Jessica Olofsson, for JavaDoc: Emilia Jarleback
 */

@Service("reservationService")
@Transactional
public class ReservationServiceProductionImpl implements ReservationService {
    private BookingDao dao;

    /**
     * Constructs a new ReservationServiceProductionImpl with the specified
     * BookingDao.
     * 
     * @param dao The {@link BookingDao} used for reservation persistence operations
     */
    public ReservationServiceProductionImpl(BookingDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Adds a new reservation by delegating to the DAO layer.
     * Rolls back the transacion if a {@link TableNotAvaliableException} is thrown.
     * </p>
     * 
     * @param reservation The {@link Reservation} to add
     * @throws TableNotAvaliableException if no suitable table is avaliable
     */
    @Override
    @Transactional(rollbackFor = TableNotAvailableException.class)
    public void addReservation(Reservation reservation) throws TableNotAvailableException {
        dao.createReservation(reservation);
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Retrieves a reservation by its unique ID from the DAO layer.
     * </p>
     * 
     * @param reservationId The unique identifies of the reservation
     * @return the {@link Reservation} if found, otherwise {@code null}
     */
    @Override
    public Reservation getReservation(String reservationId) {
        return dao.findReservation(reservationId);
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Removes a reservation with the specified ID by delegating to the DAO layer.
     * </p>
     * 
     * @param reservationId The unique identifies of the reservation to remove
     */
    @Override
    public void removeReservation(String reservationId) {
        dao.deletReservation(reservationId);
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Retrieves all reservations from the DAO layer.
     * </p>
     * 
     * @return a list of all {2link Reservation} objects
     */
    @Override
    public List<Reservation> getAllReservations() {
        return dao.allReservations();
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Retrieves all reservations for a specific table from the DAO layer.
     * </p>
     * 
     * @param tableId The unique identifier of the table
     * @return a list of {@link Reservation} objects for the specified table
     */
    @Override
    public List<Reservation> allReservationsForTable(String tableId) {
        return dao.allReservationsForTable(tableId);
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Retrieves all reservations for a specific customer from the DAO layer.
     * </p>
     * 
     * @param customerId The unique identifier of the customer
     * @return a list of {@link Reservation} objects for the specified customer
     */
    @Override
    public List<Reservation> allReservationsForCustomer(String reservationId) {
        return dao.allReservationsForCustomer(reservationId);
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Updates an existing reservation with new information by delegating to the DAO
     * layer.
     * </p>
     * 
     * @param updatedReservation Tyhe updated {@link Reservation} object
     */
    public void changeReservation(Reservation updatedReservation) {
        dao.updateReservation(updatedReservation);
    }

    // Commented code for checking if customer already exists before retrieving
    // reservations
    // @Override
    // public List<Reservation> allReservationsForCustomer(String customerID) throws
    // CustomerNotFoundException {
    // //Before geting the list of reservations, we have to know if the customer
    // excist.
    // List<Customer> allCustomers = dao.allCustomers();

    // for (Customer customer : allCustomers) {
    // //System.out.println(customer.getName() + " " + customer.getCustomerID());
    // if(customer.getCustomerID().equals(customerID)){
    // return dao.allReservationsForCustomer(customerID);
    // }
    // }

    // throw new CustomerNotFoundException();
    // }

}
