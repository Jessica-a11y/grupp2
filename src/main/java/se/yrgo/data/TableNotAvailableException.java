package se.yrgo.data;

/**
 * Exception thrown when no dining table is available for a reservation request.
 * <p>
 * This exception is typically used in the service or data access layers to
 * indicate
 * that all suitable tables are occupied or unavailable for the requested time.
 * </p>
 * 
 * @author Daniel Grahn, Jessica Olofsson
 */
public class TableNotAvailableException extends Exception {
    /**
     * Constructs a new {@code TableNotAvailableException} with a default error
     * message.
     */
    public TableNotAvailableException() {
        super("Table not available, try again");
    }
}
