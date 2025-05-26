package se.yrgo.data;

public class ReservationNotAvailable extends Exception {
    public ReservationNotAvailable() {
        super("No reservation was avaliable at this time and date, please pick another day or time");
    }
}
