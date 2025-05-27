package se.yrgo.domain;

import java.util.*;

import javax.persistence.*;

/**
 * Represents a dining table in the restaurant.
 * 
 * <p>
 * Each dining table has a unique table number, a specific number of seats,
 * an avaliability status and may have multiple reservations.
 * </p>
 * 
 * <p>
 * This class is a JPA entity mapped to a database table
 * </p>
 * 
 * @author Daniel Grahn, Emilia Jarleback
 */

@Entity
public class DiningTable {
    /**
     * The internal database identifier for the dining table (auto-generated).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * The unique table number used to identify the table
     */
    private String tableNumber;

    /**
     * The number of seats avaliable at this table
     */
    private int amountOfSeats;

    /**
     * Indicates whether the table is currently avaliable for reservation
     */
    private Boolean available;

    /**
     * The set of reservation associated with this table
     */
    @OneToMany(mappedBy = "table")
    private Set<Reservation> reservations;

    /**
     * Default constructer required by JPA
     */
    public DiningTable() {
    }

    /**
     * Constructs a new DiningTable with the specific details.
     * 
     * @param tableNumber   The unique table number
     * @param amountofSeats The number of seats at the table
     * @param avaliable     The avaliability status of the table
     */
    public DiningTable(String tableNumber, int amountOfSeats, Boolean available) {
        this.tableNumber = tableNumber;
        this.amountOfSeats = amountOfSeats;
        this.available = available;
    }

    /**
     * Marks this table as not avaliable for reservation.
     */
    public void tableNotAvailable() {
        this.available = false;
    }

    /**
     * returns the internal database ID of the table.
     * 
     * @return the database ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the unique table number.
     * 
     * @return the table number
     */
    public String getTableNumber() {
        return tableNumber;
    }

    /**
     * Returns the number of seats at the table.
     * 
     * @return the number of seats
     */
    public int getAmountOfSeats() {
        return amountOfSeats;
    }

    /**
     * Returns whether the table is avaliable for reservation.
     * 
     * @return {@code true} if avaliable, otherwise {@code false}
     */
    public Boolean getAvailable() {
        return available;
    }

    /**
     * Returns the set of reservations associated with this table.
     * 
     * @return a set of {@link Reservation} objects
     */
    public Set<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Returns a string representation of the dining table.
     * 
     * @return a formatted string with table details
     */
    @Override
    public String toString() {
        return String.format("Table number: %s - Amount of seats: %s - Available: %b", tableNumber, amountOfSeats,
                available);
    }
}
