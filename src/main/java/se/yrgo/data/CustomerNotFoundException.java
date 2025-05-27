package se.yrgo.data;

/**
 * Exception thrown when a customer cannot be found in the system.
 * <p>
 * This exception is typically used in data access or service layers
 * when an operation requires a customer that does not exist.
 * </p>
 * 
 * @author Daniel Grahn, Jessica Olofsson
 */
public class CustomerNotFoundException extends Exception {
    /**
     * Constructs a new {@code CustomerNotFoundException} with a default error
     * message.
     */
    public CustomerNotFoundException() {
        super("The customer could not be found");
    }
}
