package se.yrgo.data;

public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException() {
        super("The customer could not be found");
    }
}
