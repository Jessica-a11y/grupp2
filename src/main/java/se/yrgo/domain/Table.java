package se.yrgo.domain;
/**
 * Author Daniel Grahn 
 */
public class Table {
    private int tableNumber;
    private int amountOfSeats;
    private Boolean available;

    public Table(int tableNumber, int amountOfSeats, Boolean available) {
        this.tableNumber = tableNumber;
        this.amountOfSeats = amountOfSeats;
        this.available = available; 
    }

    public void tableNotAvailable() {
        this.available = false;
    }

    public int getTableNumber() {
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
