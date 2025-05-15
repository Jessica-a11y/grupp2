package se.yrgo.domain;

public class Table {
    private int tableNumber;
    private int amountOfSeats;
    private Boolean available;

    public Table(int tableNumber, int amountOfSeats) {
        this.tableNumber = tableNumber;
        this.amountOfSeats = amountOfSeats;
        this.available = true; 
    }

    public void tableNotAvailable() {
        this.available = false;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
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
}
