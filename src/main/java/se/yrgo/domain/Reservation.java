package se.yrgo.domain;

import java.time.*;

import javax.persistence.*;

/**
 * Author Daniel Grahn & Emilia Jarleback
 */
@Entity
public class Reservation{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String reservationId;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private DiningTable table;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDate reservationDate;
    private LocalTime reservationTime;

    public Reservation() {}

    public Reservation(String reservationId, DiningTable table, Customer customer, LocalDate reservationDate, LocalTime reservationTime) {
        this.reservationId = reservationId;
        this.table = table;
        this.customer = customer;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
    }

    public String getReservationId() {
        return reservationId;
    }

    public int getId() {
        return id;
    }

    public DiningTable getTable() {
        return table;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public LocalTime getReservationTime() {
        return reservationTime;
    }

    @Override
    public String toString() {
        return "Reservation:\n\tReservation ID: " + reservationId + 
                "\n\tTable: " + table + 
                "\n\tCustomer: " + customer + ", Date: " + reservationDate + 
                "\n\tTime: " + reservationTime;
    }

    public String info() {
        return "Reservation:" +  
                "\n\tName: " + customer.getName() + " - Email: " + customer.getEmail() + " - Phone number" + customer.getTelephone() +
                "\n\tDate: " + reservationDate + 
                "\n\tTime: " + reservationTime;
    }
} 
