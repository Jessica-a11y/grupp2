package se.yrgo.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import se.yrgo.domain.*;

@Repository
public class BookingDaoJpaImpl implements BookingDao{
    @PersistenceContext
    private EntityManager em;

    private static final String SELECT_ALL_CUSTOMERS = "select customer from Customer as customer";
    private static final String SELECT_ALL_TABLES = "select table from Table as table";
    private static final String SELECT_AVAILABLE_TABLES = "select table from Table as table where table.available = true";
    private static final String SELECT_ALL_RESERVATIONS = "select reservation from Reservation as reservation";
    private static final String SELECT_RESERVATIONS_FOR_TABLE = "select reservation from Reservation as reservation where reservation.tableId = :tableID";
    private static final String SELECT_RESERVATIONS_FOR_CUSTOMER = "select reservation from Reservation as reservation where reservation.customerID = :customerID";

    @Override
    public void createCustomer(Customer newCustomer) {
       System.out.println("using JPA");
       em.persist(newCustomer);
    }

    @Override
    public void createTable(Table newTable) {
        System.out.println("using JPA");
        em.persist(newTable);
    }

    @Override
    public List<Customer> allCustomers() {
        return em.createQuery(SELECT_ALL_CUSTOMERS, Customer.class).getResultList();
    }

    @Override
    public List<Table> allTables() {
       return em.createQuery(SELECT_ALL_TABLES, Table.class).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Table> availableTables() {
        return em.createQuery(SELECT_AVAILABLE_TABLES).getResultList();
    }

    @Override
    public void createReservation(Reservation newReservation) {
        em.persist(newReservation);
    }

    @Override
    public List<Reservation> allReservations() {
        return em.createQuery(SELECT_ALL_RESERVATIONS, Reservation.class).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Reservation> allReservationsForTable(String tableId) {
        return em.createQuery(SELECT_RESERVATIONS_FOR_TABLE).setParameter("tableID", tableId).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Reservation> allReservationsForCustomer(String customerID) {
        return em.createQuery(SELECT_RESERVATIONS_FOR_CUSTOMER).setParameter("customerID", customerID).getResultList();
    }
}