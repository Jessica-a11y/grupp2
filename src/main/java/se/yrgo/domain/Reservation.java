package se.yrgo.domain;

import java.time.*;

import javax.persistence.*;

/**
 * Represents a reservation for a dining table in the restaurant.
 * 
 * <p>
 * Each reservation is associated with a specific tablem customer, date ad time.
 * This class is a JPA entity mapped to a database table.
 * </p>
 * 
 * @author Daniel Grahn, Emilia Jarleback
 */

@Entity
public class Reservation{
    /**
     * The internal database identifier for the reservation (Auto-generated).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * The unique reservation identifier used within the application.
     */
    private String reservationId;

    /**
     * The dining table associated with this reservation.
     */
    @ManyToOne
    @JoinColumn(name = "table_id")
    private DiningTable table;

    /**
     * The customer who made the reservation.
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * The date of the reservation.
     */
    private LocalDate reservationDate;

    /**
     * The time of the reservation.
     */
    private LocalTime reservationTime;

    /**
     * Default constructor required by JPA
     */
    public Reservation() {}

    /**
     * Constructs a new reservation with the specified details.
     * 
     * @param reservationId The unique reservation identifier
     * @param table The dining table for the reservation
     * @param customer The customer who made the reservation
     * @param reservationDate The date of the reservation
     * @param reservationTime The time of the reservation
     */
    public Reservation(String reservationId, DiningTable table, Customer customer, LocalDate reservationDate, LocalTime reservationTime) {
        this.reservationId = reservationId;
        this.table = table;
        this.customer = customer;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
    }

    /**
     * Returns the unique reservation identifier.
     * 
     * @return the reservation ID
     */
    public String getReservationId() {
        return reservationId;
    }

    /**
     * Returns the internal database ID of the reservation.
     * 
     * @return the database ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the dining table associated with this reservation.
     * 
     * @return the {@link DiningTable} object
     */
    public DiningTable getTable() {
        return table;
    }

    /**
     * Returns the customer who made the reservation.
     * 
     * @return teh {@link Customer} object
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Returns the date of the reservation.
     * 
     * @return the reservation date
     */
    public LocalDate getReservationDate() {
        return reservationDate;
    }

    /**
     * Returns the time of the reservation
     * 
     * @return the reservation time
     */
    public LocalTime getReservationTime() {
        return reservationTime;
    }

    /**
     * Returns a string representation of the reservation, including all details.
     * 
     * @return a formatted string with reservation details
     */
    @Override
    public String toString() {
        return "Reservation:\n\tReservation ID: " + reservationId + 
                "\n\tTable: " + table + 
                "\n\tCustomer: " + customer + ", Date: " + reservationDate + 
                "\n\tTime: " + reservationTime;
    }

    /**
     * Returns a customer-focused summary of the reservation.
     * 
     * @return a formatted string with customer and reservation details
     */
    public String info() {
        return "Reservation:" +  
                "\n\tName: " + customer.getName() + " - Email: " + customer.getEmail() + " - Phone number: " + customer.getTelephone() +
                "\n\tDate: " + reservationDate + 
                "\n\tTime: " + reservationTime + "\n";
    }
} 
