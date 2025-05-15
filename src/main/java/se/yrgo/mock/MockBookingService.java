package se.yrgo.mock;

import se.yrgo.domain.*;

import java.time.*;
import java.util.*;

public class MockBookingService {
    private Map<String, Customer> customers = new HashMap<>();
    private Map<String, Reservation> reservations = new HashMap<>();
    private Map<Integer, Table> tables = new HashMap<>();

    public MockBookingService() {
        tables.put(1, new Table(1, 4, false));
        tables.put(2, new Table(2, 2, true));
        tables.put(3, new Table(3, 6, true));

        //Adding some customers
        customers.put("1", new Customer("1", "Anna Andersson", "anna@gmail.com", "0701234567"));
        customers.put("2", new Customer("2","Bertil Bengtsson", "bertil@gmail.com", "0709876543"));

        //Adding some bookings
        reservations.put("r1", new Reservation(
            "r1", "1", "1",
            LocalDate.now().plusDays(1),
            LocalTime.of(18, 0)
        ));
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerID(), customer);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    public void addTable(Table table) {
        tables.put(table.getTableNumber(), table);
    }

    public List<Table> getAllTables() {
        return new ArrayList<>(tables.values());
    }

    public void addReservation(Reservation reservation) {
        String reservationId = UUID.randomUUID().toString();
        reservations.put(reservationId, reservation);
    }

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations.values());
    }

    //Delete a booking
    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation.getReservationId());
    }
}
