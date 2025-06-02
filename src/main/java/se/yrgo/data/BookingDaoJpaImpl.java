package se.yrgo.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import se.yrgo.domain.*;

/**
 * JPA implementation of {@link BookingDao} that handles database operations for
 * reservations, customers and dining tables.
 * 
 * <p>
 * This class uses the JPA {@link EntityManager} to perform CRUD operations and
 * queries.
 * </p>
 * 
 * @author Daniel Grahn, Jessica Olofsson, Emilia Jarleback
 */

@Repository
public class BookingDaoJpaImpl implements BookingDao {
    @PersistenceContext
    private EntityManager em;

    private static final String SELECT_ALL_RESERVATIONS = "select reservation from Reservation as reservation";
    private static final String SELECT_ALL_CUSTOMERS = "select customer from Customer as customer";
    private static final String SELECT_ALL_TABLES = "select table from DiningTable as table";

    private static final String SELECT_AVAILABLE_TABLES = "select table from DiningTable as table where table.available = true";
    private static final String SELECT_RESERVATIONS_FOR_TABLE = "select reservation from Reservation as reservation where reservation.tableId = :tableID";
    private static final String SELECT_RESERVATIONS_FOR_CUSTOMER = "select reservation from Reservation as reservation where reservation.reservationId = :reservationID";

    private static final String UPDATE_RESERVATION = "UPDATE Reservation as r SET r.reservationDate = :reservationDate, r.reservationTime = :reservationTime WHERE r.reservationId = :reservationId";
    private static final String UPDATE_CUSTOMER = "UPDATE Customer as c SET c.name = :name, c.email = :email, c.telephone = :telephone";
    private static final String UPDATE_Table = "UPDATE DiningTable as t SET t.amountOfSeats = :amountOfSeats, t.available = :available WHERE t.tableNumber = :tableNumber";

    private static final String DELET_RESERVATION = "DELETE FROM Reservation as r WHERE r.reservationId = :reservationId";
    private static final String DELET_CUSTOMER = "DELETE FROM Customer as c WHERE c.customerID = :customerID";
    private static final String DELET_Table = "DELETE FROM DiningTable as t WHERE t.tableNumber = :tableNumber";

    private static final String FIND_TABLE_SQL = "select table from DiningTable as table where table.tableNumber = :tableNumber";
    private static final String FIND_CUSTOMER_SQL = "select customer from Customer as customer where customer.customerID = :customerId"; 
    private static final String FIND_RESERVATION_SQL = "select r from Reservation as r where r.reservationId = :reservationId";

    private static final String AVAILABILITY_SQL = "UPDATE DiningTable as t SET t.available = false where t.tableNumber = :tableNumber";

    /**
     * Uses JPA to add a reservation into the database.
     * 
     * @param newReservation The {@link Reservation} to add
     */
    @Override
    public void createReservation(Reservation newReservation) {
        em.persist(newReservation);
    }

    /**
     * Uses JPA to add a customer into the database.
     * 
     * @param newCustomer the {@link Customer} to add
     */
    @Override
    public void createCustomer(Customer newCustomer) {
        em.persist(newCustomer);
    }

    /**
     * Uses JPA to add a dining table into the database.
     * 
     * @param newTable The {@link DiningTable} to add
     */
    @Override
    public void createTable(DiningTable newTable) {
        em.persist(newTable);
    }

    /**
     * Returns a list of all customers in the database.
     * 
     * @return a list of all {@link Customer} objects
     */
    @Override
    public List<Customer> allCustomers() {
        return em.createQuery(SELECT_ALL_CUSTOMERS, Customer.class).getResultList();
    }

    /**
     * Returns all tables in the database.
     * 
     * @return a list of all {@link DiningTable} objects
     */
    @Override
    public List<DiningTable> allTables() {
        return em.createQuery(SELECT_ALL_TABLES, DiningTable.class).getResultList();
    }

    /**
     * Returns a list of all reservations in the database.
     * 
     * @return a list of all {@link Reservation} objects
     */
    @Override
    public List<Reservation> allReservations() {
        return em.createQuery(SELECT_ALL_RESERVATIONS, Reservation.class).getResultList();
    }

    /**
     * Returns a list of all dining tables that are avaialable (true).
     * 
     * @return a list of avaliable {@link DiningTable} objects
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<DiningTable> availableTables() {
        return em.createQuery(SELECT_AVAILABLE_TABLES).getResultList();
    }

    /**
     * Returns a list of all reservations for a certain table.
     * 
     * @param tableId The table's unique identifier
     * @return a list of {@link Reservation} objects for the specified table
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Reservation> allReservationsForTable(String tableId) {
        return em.createQuery(SELECT_RESERVATIONS_FOR_TABLE).setParameter("tableID", tableId).getResultList();
    }

    /**
     * Returns all reservations for a certain customer.
     * 
     * @param customerId the customer's unique identifier
     * @return a list of {@link Reservation} objects for the specified customer
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Reservation> allReservationsForCustomer(String reservationId) {
        return em.createQuery(SELECT_RESERVATIONS_FOR_CUSTOMER).setParameter("reservationID", reservationId)
                .getResultList();
    }

    /**
     * Updates a reservations time and date.
     * 
     * @param changedReservation the {@link Reservation} with updated information
     */
    @Override
    public void updateReservation(Reservation changedReservation) {
        em.createNativeQuery(UPDATE_RESERVATION)
                .setParameter("reservationDate", changedReservation.getReservationDate())
                .setParameter("reservationTime", changedReservation.getReservationTime())
                .setParameter("reservationId", changedReservation.getReservationId())
                .executeUpdate();
    }

    /**
     * Updates a customer.
     * 
     * @param changedCustomer the {@link Customer} with updated information
     */
    @Override
    public void updateCustomer(Customer changedCustomer) {
        em.createNativeQuery(UPDATE_CUSTOMER)
                .setParameter("name", changedCustomer.getName())
                .setParameter("email", changedCustomer.getEmail())
                .setParameter("telephone", changedCustomer.getTelephone())
                .executeUpdate();
    }

    /**
     * Updates a dining table.
     * 
     * @param changedTable the {@link DiningTable} with updated information
     */
    @Override
    public void updateTable(DiningTable changedTable) {
        em.createNativeQuery(UPDATE_Table)
                .setParameter("amountOfSeats", changedTable.getAmountOfSeats())
                .setParameter("available", changedTable.getAvailable())
                .setParameter("tableNumber", changedTable.getTableNumber())
                .executeUpdate();
    }

    /**
     * Deletes a certain dining table from the database.
     * 
     * @param reservationId the unique identifier of the reservation to delete
     */
    @Override
    public void deletReservation(String reservationId) {
        em.createQuery(DELET_RESERVATION)
                .setParameter("reservationId", reservationId)
                .executeUpdate();
    }

    /**
     * Deletes a certain customer from the database.
     * 
     * @param customer the {@link Customer} to delete
     */
    @Override
    public void deletCustomer(Customer customer) {
        em.createQuery(DELET_CUSTOMER)
                .setParameter("customerID", customer.getCustomerID())
                .executeUpdate();
    }

    /**
     * Deletes a certain table from the database.
     * 
     * @param table the {@link DiningTable} to delete
     */
    @Override
    public void deletTable(DiningTable table) {
        em.createQuery(DELET_Table)
                .setParameter("tableNumber", table.getTableNumber())
                .executeUpdate();
    }

    /**
     * Returns a certain dining table, based of it't table number.
     * 
     * @param tableNumber the unique table number
     * @return the {@link DiningTable} if found, otherwise {@code null}
     */
    @Override
    public DiningTable findTableById(String tableNumber) {
        return (DiningTable) em.createQuery(FIND_TABLE_SQL).setParameter("tableNumber", tableNumber).getSingleResult();
    }

    /**
     * Returns a customer basen of it's customerId.
     * 
     * @param customerId the unique customer ID
     * @return the {@link Customer} if found, otherwise {@code null}
     */
    @Override
    public Customer findCustomer(String customerId) {
        return (Customer) em.createQuery(FIND_CUSTOMER_SQL).setParameter("customerId", customerId).getSingleResult();
    }

    /**
     * Returns a reservation basen on the reservationId.
     * 
     * @param reservationId the unique reservationId
     * @return the {@link Reservation} if found, otherwise {@code null}
     */
    @Override
    public Reservation findReservation(String reservationId) {
        return (Reservation) em.createQuery(FIND_RESERVATION_SQL).setParameter("reservationId", reservationId)
                .getSingleResult();
    }

    /**
     * Changes the avaialility for a certain table.
     * 
     * @param tableNumber the unique table number
     */
    @Override
    public void changeAvailability(String tableNumber) {
        em.createQuery(AVAILABILITY_SQL).setParameter("tableNumber", tableNumber).executeUpdate();
    }
}