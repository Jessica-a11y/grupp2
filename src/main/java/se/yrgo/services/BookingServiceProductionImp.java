package se.yrgo.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.data.*;
import se.yrgo.domain.*;

/**
 * Production implementation of the {@link BookingService} interface.
 * 
 * <p>
 * This service coordinates customer, table and reservation management to handle
 * the full booking process for a restaurant.
 * </p>
 * 
 * <p>
 * This class is annotated as a Spring {@code @Service} and is transactional.
 * </p>
 * 
 * @author Daniel Grahn, Jessica Olofsson, for JavaDoc: Emilia Jarleack
 */

@Service("bookingService")
@Transactional
public class BookingServiceProductionImp implements BookingService {
    private CustomerService customerService;
    private TableService tableService;
    private ReservationService reservationService;

    /**
     * Constructs a new BookingServiceProductionImp with the required dependencies
     * 
     * @param cs The customer service to manage customer data
     * @param ts The table service to manage dining table data
     * @param rs The reservation service to manage reservation data
     */
    public BookingServiceProductionImp(CustomerService cs, TableService ts, ReservationService rs) {
        this.customerService = cs;
        this.tableService = ts;
        this.reservationService = rs;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Creates a reservation by checking for avaliable time and date, finding a
     * suitable table
     * and either retrievling or creating a customer.
     * </p>
     * 
     * @throws TableNotAvaliableException if no suitable table is avaliable
     * @throws ReservationNotAvaliable    of the reservation slot is already taken
     */
    @Override
    public void makeReservation(String date, String time, int amoutOfSeats, String fullName, String email,
            String number) throws TableNotAvailableException, ReservationNotAvailable {
        try {
            checkForAvailableTimeAndDate(date, time);
            DiningTable tableToBook = checkForAvailableDiningTable(amoutOfSeats);
            Customer newCustomer = checkForAlreadyExcistingCustomer(fullName, email, number);
            reservationService.addReservation(new Reservation("r4", tableToBook, newCustomer, LocalDate.parse(date), LocalTime.parse(time)));
        } catch (TableNotAvailableException e) {
            throw new TableNotAvailableException();
        } catch (ReservationNotAvailable ex) {
            throw new ReservationNotAvailable();
        }
    }

    /**
     * Checks if the specified date and time are avaliable for reservation.
     * 
     * @param date The date to check (format: "yyyy-MM-dd")
     * @param time The time to check (format: "HH:mm")
     * @throws ReservationNotAvaliable if the slot is already reserved
     */
    public void checkForAvailableTimeAndDate(String date, String time) throws ReservationNotAvailable {
        List<Reservation> allReservations = reservationService.getAllReservations();
        for (Reservation r : allReservations) {
            if ((r.getReservationDate() == LocalDate.parse(date))
                    && (r.getReservationTime() == LocalTime.parse(time))) {
                throw new ReservationNotAvailable();
            }
        }
    }

    /**
     * Finds an avaliable dining table that can accomodate the specified number of
     * seats.
     * 
     * @param amountOfSeats the required number of seats
     * @return a suitable {@link DiningTable}
     * @throws TableNotAvaliableException if no suitable table is fund
     */
    public DiningTable checkForAvailableDiningTable(int amoutOfSeats) throws TableNotAvailableException {
        List<DiningTable> tableList = tableService.getAllAvailableTables();
        for (DiningTable dt : tableList) {
            if (dt.getAmountOfSeats() >= amoutOfSeats) {
                return dt;
            }
        }
        throw new TableNotAvailableException();
    }

    /**
     * Checks if a customer with the specified name and email already exists.
     * If not, creates and adds a new customer.
     * 
     * @param fullName The customer's full name
     * @param email    The customer's email address
     * @param number   The customer's phone number
     * @return the existing or newly created {@link Customer}
     */
    public Customer checkForAlreadyExcistingCustomer(String fullName, String email, String number) {
        List<Customer> customers = customerService.getAllCustomers();
        for (Customer c : customers) {
            if ((fullName == c.getName()) && (email == c.getEmail())) {
                return c;
            }
        }
        Customer newCustomer = new Customer("14", fullName, email, number);
        customerService.addCustomer(newCustomer);
        return newCustomer;
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Deletes a reservation with the specified ID.
     * </p>
     * 
     * @param reservationId The ID of the reservation to delete
     */
    @Override
    public void deleteReservatuion(String reservationId) {
        reservationService.removeReservation(reservationId);
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Finds and returns a reservation by its ID.
     * </p>
     * 
     * @param reservationID The ID of the reservation to find
     * @return the {@link Reservation} if found, otherwise null
     */
    @Override
    public Reservation findReservation(String reservationID) {
        return reservationService.getReservation(reservationID);
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Displayes avaliable tables. The current implementation prints a message to
     * the console.
     * </p>
     */
    @Override
    public void availableTables() {
        System.out.println("Avaliable tables");
    }

    /**
     * Updates the date and time of an existing reservation.
     * 
     * <p>
     * Retrieves the reservation with the specified ID, updates its date and time
     * and persists the changes. If the reservation is not found, an
     * {@link IllegalArgumentException} is thrown.
     * </p>
     * 
     * @param reservationId The unique identifier of the reservation to update
     * @param date          The new reservation date (format: "yyyy-MM-dd")
     * @param time          The new reservation time (format: "HH:mm")
     * @throws IllegalArgumentsException if no reservation is found with the
     *                                   specified ID
     */
    @Override
    public void updateReservation(String reservationId, String date, String time) {
        Reservation result = reservationService.getReservation(reservationId);
        if (result != null) {
            result.setReservationDate(LocalDate.parse(date));
            result.setReservationTime(LocalTime.parse(time));
            reservationService.changeReservation(result);
        } else {
            throw new IllegalArgumentException("Reservation not found with ID: " + reservationId);
        }
    }

}
