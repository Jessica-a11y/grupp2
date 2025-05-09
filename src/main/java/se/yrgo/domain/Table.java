package se.yrgo.domain;

public class Table {
    private int tableNumber;
    private int amountOfSeats;
    private Boolean available;

    public Table(int tableNumber, int amountOfSeats, Boolean available) {
        this.tableNumber = tableNumber;
        this.amountOfSeats = amountOfSeats;
        this.available = true;
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
}