package se.yrgo.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import se.yrgo.domain.*;

@Repository
public class BookingDaoJdbcImpl implements BookingDao{
    @PersistenceContext
    private EntityManager em;

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
        return em.createQuery("select customer from Customer as customer", Customer.class).getResultList();
    }

    @Override
    public List<Table> allTables() {
       return em.createQuery("select table from Table as table", Table.class).getResultList();
    }

    @Override
    public List<Table> availableTables() {
        return em.createQuery("select table from Table as table where table.available = true").getResultList();
    }

    @Override
    public void createReservation(Reservation newReservation) {
        em.persist(newReservation);
    }

    @Override
    public List<Reservation> allReservations() {
        return em.createQuery("select reservation from Reservation as reservation", Reservation.class).getResultList();
    }

    @Override
    public List<Reservation> allReservationsForTable(Table table) {
        return em.createQuery("select reservation from Reservation as reservation where reservation.tableId = :tableID").setParameter("tableID", table.getTableNumber()).getResultList();
    }

    @Override
    public List<Reservation> allReservationsForCustomer(String customerID) {
        return em.createQuery("select reservation from Reservation as reservation where reservation.customerID = :customerID").setParameter("customerID", customerID).getResultList();
    }

  

    
}