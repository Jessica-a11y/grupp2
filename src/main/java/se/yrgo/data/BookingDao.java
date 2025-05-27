package se.yrgo.data;

import se.yrgo.domain.*;
import java.util.*;

/**
 * BookingDao defines the data access operations for reservations, customers and
 * dining tables.
 * 
 * <p>
 * This interface provides CRUD (Create, Read, Update and Delete) methods for
 * managing reservations, customers and tables, as well as various queries for
 * retrieving related data.
 * </p>
 * 
 * <p>
 * Implementations of this interface are responsible for interacting woth the
 * underlying data source (e.g. a database).
 * </p>
 * 
 * @author Jessica Olofsson
 */

public interface BookingDao {
    // --- Create operations ---

    /**
     * Persists a new reservation in the data store.
     * 
     * @param newReservation The {@link Reservation} to create
     */
    public void createReservation(Reservation newReservation);

    /**
     * Persists a new customer in the data store.
     *
     * @param newCustomer the {@link Customer} to create
     */
    public void createCustomer(Customer newCustomer);

    /**
     * Persists a new dining table in the data store.
     *
     * @param newTable the {@link DiningTable} to create
     */
    public void createTable(DiningTable newTable);

    // --- Read operations (get by id) ---

    /**
     * Retrieves a dining table by its unique ID.
     *
     * @param tableId the unique identifier of the table
     * @return the {@link DiningTable} if found, otherwise {@code null}
     */
    public DiningTable findTableById(String tableId);

    /**
     * Retrieves a customer by their unique ID.
     *
     * @param customerId the unique identifier of the customer
     * @return the {@link Customer} if found, otherwise {@code null}
     */
    public Customer findCustomer(String customerId);

    /**
     * Retrieves a reservation by its unique ID.
     *
     * @param reservationId the unique identifier of the reservation
     * @return the {@link Reservation} if found, otherwise {@code null}
     */
    public Reservation findReservation(String reservationId);

    // --- Read operations (get all) ---

    /**
     * Retrieves a list of all customers.
     *
     * @return a {@link List} of all {@link Customer} objects
     */
    public List<Customer> allCustomers();

    /**
     * Retrieves a list of all dining tables.
     *
     * @return a {@link List} of all {@link DiningTable} objects
     */
    public List<DiningTable> allTables();

    /**
     * Retrieves a list of all reservations.
     *
     * @return a {@link List} of all {@link Reservation} objects
     */
    public List<Reservation> allReservations();

    /**
     * Retrieves a list of all available dining tables.
     *
     * @return a {@link List} of available {@link DiningTable} objects
     */
    public List<DiningTable> availableTables();

    /**
     * Retrieves all reservations for a specific table.
     *
     * @param tableId the unique identifier of the table
     * @return a {@link List} of {@link Reservation} objects for the specified table
     */
    public List<Reservation> allReservationsForTable(String tableId);

    /**
     * Retrieves all reservations for a specific customer.
     *
     * @param customerId the unique identifier of the customer
     * @return a {@link List} of {@link Reservation} objects for the specified
     *         customer
     */
    public List<Reservation> allReservationsForCustomer(String reservationId);

    // --- Update operations ---

    /**
     * Updates an existing reservation in the data store.
     *
     * @param changedReservation the {@link Reservation} with updated information
     */
    public void updateReservation(Reservation changedReservation);

    /**
     * Updates an existing customer in the data store.
     *
     * @param changedCustomer the {@link Customer} with updated information
     */
    public void updateCustomer(Customer changedCustomer);

    /**
     * Updates an existing dining table in the data store.
     *
     * @param changedTable the {@link DiningTable} with updated information
     */
    public void updateTable(DiningTable changedTable);

    /**
     * Changes the availability status of a table.
     *
     * @param tableNumber the unique identifier or number of the table
     */
    public void changeAvailability(String tableNumber);

    // --- Delete operations ---

    /**
     * Deletes a reservation with the specified ID.
     *
     * @param reservationId the unique identifier of the reservation to delete
     */
    public void deletReservation(String reservationId);

    /**
     * Deletes a customer from the data store.
     *
     * @param customer the {@link Customer} to delete
     */
    public void deletCustomer(Customer customer);

    /**
     * Deletes a dining table from the data store.
     *
     * @param table the {@link DiningTable} to delete
     */
    public void deletTable(DiningTable table);
}