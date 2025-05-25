package se.yrgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Author Daniel Grahn 
 */
@Entity
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String tableNumber;
    private int amountOfSeats;
    private Boolean available;

    public Table() {}

    public Table(String tableNumber, int amountOfSeats, Boolean available) {
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

    @Override
    public String toString(){ 
        return String.format("Table number: %s - Amount of seats: %s - Available: %b", tableNumber, amountOfSeats, available);
    }
}
