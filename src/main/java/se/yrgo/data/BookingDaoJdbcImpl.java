package se.yrgo.data;

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
}