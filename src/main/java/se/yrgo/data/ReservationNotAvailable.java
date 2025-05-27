package se.yrgo.data;

/**
 * Exception thrown when a reservation cannot be made because the requested
 * time and date are not available.
 * <p>
 * This exception is typically used in service or data access layers to indicate
 * that the user should select a different date or time for their reservation.
 * </p>
 * 
 * @author Daniel Grahn, Jessica Olofsson
 */
public class ReservationNotAvailable extends Exception {
    /**
     * Constructs a new {@code ReservationNotAvailable} exception with a default
     * error message.
     */
    public ReservationNotAvailable() {
        super("No reservation was available at this time and date, please pick another day or time");
    }
}
