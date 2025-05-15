package se.yrgo.mock;

import se.yrgo.domain.*;

import java.time.*;
import java.util.*;

public class MockBookingService {
    private Map<String, Customer> customers = new HashMap<>();
    private Map<String, Reservation> reservations = new HashMap<>();
    private Map<Integer, Table> tables = new HashMap<>();

    public MockBookingService() {
        tables.put(1, new Table(1, 4));
        tables.put(2, new Table(2, 2));
        tables.put(3, new Table(3, 6));

        //Adding some customers
        customers.put("1", new Customer("Anna Andersson", "anna@gmail.com", "0701234567"));
        customers.put("2", new Customer("Bertil Bengtsson", "bertil@gmail.com", "0709876543"));

        //Adding some bookings
        reservations.put("r1", new Reservation(
            "r1", "1", "1",
            LocalDate.now().plusDays(1),
            LocalTime.of(18, 0)
        ));
    }

    public Customer addCustomer(String name, String email, String telephone) {
        String id = UUID.randomUUID().toString();
        Customer customer = new Customer(name, email, telephone);
        customers.put(id, customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    public Table addTable(int tableNumber, int amountOfSeats) {
        Table table = new Table(tableNumber, amountOfSeats);
        tables.put(tableNumber, table);
        return table;
    }

    public List<Table> getAllTables() {
        return new ArrayList<>(tables.values());
    }

    public Reservation addReservation(String tableId, String customerId, LocalDate date, LocalTime time) {
        String reservationId = UUID.randomUUID().toString();
        Reservation reservation = new Reservation(reservationId, tableId, customerId, date, time);
        reservations.put(reservationId, reservation);

        //Mark table as booked
        Table table = tables.get(Integer.parseInt(tableId));

        if (table != null) {
            table.tableNotAvailable();
        }

        return reservation;
    }

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations.values());
    }

    //Delete a booking
    public void removeReservation(String reservationId) {
        Reservation reservation = reservations.remove(reservationId);

        if (reservation != null) {
            //Mark table as avaliable again
            Table table = tables.get(Integer.parseInt(reservation.getTableId()));

            if (table != null) {
                table.setAvailable(true);
            }
        }
    }
}
