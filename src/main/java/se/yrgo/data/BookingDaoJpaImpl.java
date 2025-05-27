package se.yrgo.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import se.yrgo.domain.*;

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

    // Create
    @Override
    public void createReservation(Reservation newReservation) {
        em.persist(newReservation);
    }

    @Override
    public void createCustomer(Customer newCustomer) {
        System.out.println("using JPA");
        em.persist(newCustomer);
    }

    @Override
    public void createTable(DiningTable newTable) {
        System.out.println("using JPA");
        em.persist(newTable);
    }

    // get all
    @Override
    public List<Customer> allCustomers() {
        return em.createQuery(SELECT_ALL_CUSTOMERS, Customer.class).getResultList();
    }

    @Override
    public List<DiningTable> allTables() {
        return em.createQuery(SELECT_ALL_TABLES, DiningTable.class).getResultList();
    }

    @Override
    public List<Reservation> allReservations() {
        return em.createQuery(SELECT_ALL_RESERVATIONS, Reservation.class).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DiningTable> availableTables() {
        return em.createQuery(SELECT_AVAILABLE_TABLES).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Reservation> allReservationsForTable(String tableId) {
        return em.createQuery(SELECT_RESERVATIONS_FOR_TABLE).setParameter("tableID", tableId).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Reservation> allReservationsForCustomer(String reservationId) {
        return em.createQuery(SELECT_RESERVATIONS_FOR_CUSTOMER).setParameter("reservationID", reservationId).getResultList();
    }

    // Update
    @Override
    public void updateReservation(Reservation changedReservation) {
        em.createNativeQuery(UPDATE_RESERVATION)
                .setParameter("reservationDate", changedReservation.getReservationDate())
                .setParameter("reservationTime", changedReservation.getReservationTime())
                .setParameter("reservationId", changedReservation.getReservationId())
                .executeUpdate();
    }

    @Override
    public void updateCustomer(Customer changedCustomer) {
        em.createNativeQuery(UPDATE_CUSTOMER)
                .setParameter("name", changedCustomer.getName())
                .setParameter("email", changedCustomer.getEmail())
                .setParameter("telephone", changedCustomer.getTelephone())
                .executeUpdate();
    }

    @Override
    public void updateTable(DiningTable changedTable) {
        em.createNativeQuery(UPDATE_Table)
                .setParameter("amountOfSeats", changedTable.getAmountOfSeats())
                .setParameter("available", changedTable.getAvailable())
                .setParameter("tableNumber", changedTable.getTableNumber())
                .executeUpdate();
    }

    // Delet
    @Override
    public void deletReservation(String reservationId) {
        // em.createQuery(DELET_RESERVATION).setParameter("reservationId", reservationId);
        em.createQuery(DELET_RESERVATION)
                .setParameter("reservationId", reservationId)
                .executeUpdate();
    }

    @Override
    public void deletCustomer(Customer customer) {
        // em.remove(customer.getId());

        em.createQuery(DELET_CUSTOMER)
                .setParameter("customerID", customer.getCustomerID())
                .executeUpdate();
    }

    @Override
    public void deletTable(DiningTable table) {
        // em.remove(table.getId());

        em.createQuery(DELET_Table)
                .setParameter("tableNumber", table.getTableNumber())
                .executeUpdate();
    }

    @Override
    public DiningTable findTableById(String tableNumber) {
        return (DiningTable) em.createQuery("select table from DiningTable as table where table.tableNumber = :tableNumber").setParameter("tableNumber", tableNumber).getSingleResult();
    }

    @Override
    public Customer findCustomer(String customerId) {
        return (Customer) em.createQuery("select customer from Customer as customer where customer.customerID = :customerId").setParameter("customerId", customerId).getSingleResult();
    }

    @Override
    public Reservation findReservation(String reservationId) {
        return (Reservation) em.createQuery("select reservation from Reservation as reservation where reservation.reservationId = :reservationId").setParameter("reservationId", reservationId).getSingleResult();
    }

    @Override
    public void changeAvailability(String tableNumber) {
        em.createQuery("UPDATE DiningTable as t SET t.available = false where t.tableNumber = :tableNumber").setParameter("tableNumber", tableNumber).executeUpdate();
    }
}