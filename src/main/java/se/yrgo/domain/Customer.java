package se.yrgo.domain;

import java.util.*;

import javax.persistence.*;

/**
 * Represents a customer in the booking system.
 * 
 * <p>
 * Each customer has a unique customer ID, name, email, telephone number
 * and may have multiple reservations.
 * </p>
 * 
 * <p>
 * This class is a JPA entity mapped to a database table.
 * </p>
 * 
 * @author Jessica Olofsson, Emilia jarleback
 */

@Entity
public class Customer {
    /**
     * The internal database identifierfor the customer (auto-generated).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * The unique customer identifier used within the application.
     */
    private String customerID;

    /**
     * The customer's full name.
     */
    private String name;

    /**
     * The customer's email address.
     */
    private String email;

    /**
     * The customer's phone number.
     */
    private String telephone;

    /**
     * The set of reservations associated with this customer
     */
    @OneToMany(mappedBy = "customer")
    private Set<Reservation> reservations;

    /**
     * Default constructor required by JPA
     */
    public Customer() {
    }

    /**
     * Constructs a new Customer with the specified details
     * 
     * @param customerID The unique customer identifier
     * @param name       The customer's full name
     * @param email      The customer's email address
     * @param telephone  The customer's telephone number
     */
    public Customer(String customerID, String name, String email, String telephone) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
    }

    /**
     * returns the internal database ID of the customer.
     * 
     * @return the database ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the unique customer identifier.
     * 
     * @return the customer ID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Returns the customer's ful nane.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the customer's email address
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the customer's phone number
     * 
     * @return the phone number
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Returns the set of reservations associated with this customer.
     * 
     * @return a set of {@link Reservation} objects
     */
    public Set<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Returns a string representation of the customer.
     * 
     * @return a formatted string with customer details
     */
    @Override
    public String toString() {
        return String.format("Customer ID: %s - Name: %s - email: %s - Telephone_Number: %s", customerID, name, email,
                telephone);
    }

}
