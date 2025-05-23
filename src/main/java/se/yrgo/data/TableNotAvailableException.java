package se.yrgo.data;

public class TableNotAvailableException extends Exception{
    public TableNotAvailableException() {
        super("Table not avalibale, try again");
    }
}
