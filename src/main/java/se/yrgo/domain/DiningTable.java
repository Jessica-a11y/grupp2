package se.yrgo.domain;

import java.util.*;

import javax.persistence.*;

/**
 * Author Daniel Grahn & Emilia Jarleback
 */
@Entity
public class DiningTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String tableNumber;
    private int amountOfSeats;
    private Boolean available;

    @OneToMany(mappedBy = "table")
    private Set<Reservation> reservations;

    public DiningTable() {
    }

    public DiningTable(String tableNumber, int amountOfSeats, Boolean available) {
        this.tableNumber = tableNumber;
        this.amountOfSeats = amountOfSeats;
        this.available = available;
    }

    public void tableNotAvailable() {
        this.available = false;
    }

    public int getId() {
        return id;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public int getAmountOfSeats() {
        return amountOfSeats;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public String toString() {
        return String.format("Table number: %s - Amount of seats: %s - Available: %b", tableNumber, amountOfSeats,
                available);
    }
}
