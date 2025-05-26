package se.yrgo;

import se.yrgo.services.*;
import se.yrgo.data.*;
import se.yrgo.domain.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.*;

import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SimpleBookingServiceTest {
    ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
    BookingService service = container.getBean("bookingService", BookingService.class);
    CustomerService customerService = container.getBean("customerService", CustomerService.class);
    TableService tableService = container.getBean("tableService", TableService.class);
    ReservationService reservationService = container.getBean("reservationService", ReservationService.class);

    @Test
    public void makeReservation() {

        // service.addCustomer(new Customer("123", "John Doe", "doe.john@gmail.com",
        // "0707080908"));
        // ArrayList<Customer> customerList = (ArrayList<Customer>)
        // service.getAllCustomers();
        // Customer theCustomer = customerList.get(0);

        // assertEquals(theCustomer.getCustomerID(), "123", "Check if right id");
        // assertEquals(theCustomer.getName(), "John Doe", "Check if the name is
        // right");
    }

    @Test
    public void updateReservation() {
        // Table newTable = new Table("1", 4, false);
        // service.addTable(newTable);
    }

    @Test
    public void deleteReservatuion() {
        // Customer newCustomer = new Customer("123", "John Doe", "doe.john@gmail.com",
        // "0707080908");
        // Table newTable = new Table("1", 4, false);
        // String tableId = String.valueOf(newTable.getId());

        // service.addCustomer(newCustomer);
        // service.addTable(newTable);

        // Reservation newReservation = new Reservation("r1", tableId,
        // newCustomer.getCustomerID(), LocalDate.now(), LocalTime.of(18, 0));

        // assertEquals(tableId, newReservation.getTableId());
        // assertEquals("123", newReservation.getCustomerId());

        // try {
        // service.addReservation(newReservation);
        // } catch (TableNotAvailableException e) {
        // System.err.println("Something went wrong with addReservation()");
        // System.err.println(e.getMessage());
        // }

        // ArrayList<Reservation> allReservation = (ArrayList<Reservation>)
        // service.getAllReservations();
        // Reservation theReservationFromAbove = allReservation.get(0);

        // assertEquals("r1", theReservationFromAbove.getReservationId());
    }

    @Test
    public void findReservation() {
        Customer customer = new Customer("c1", "John Doe", "doe.john@gmail.com", "0707080908");
        DiningTable table = new DiningTable("t1", 4, true);

        customerService.addCustomer(customer);
        tableService.addTable(table);

        Reservation reservation = new Reservation("r1", table, customer, LocalDate.now(), LocalTime.of(18, 0));
        
        ArrayList<Reservation> firstReservationList = (ArrayList<Reservation>) service.findReservation("r1");
        assertEquals(0, firstReservationList.size(),"no new reservation but it eather 0 (it should be null)");
       
        try {
            reservationService.addReservation(reservation);
        } catch (Exception e) {
            
        }

        ArrayList<Reservation> reservationsList = (ArrayList<Reservation>) service.findReservation("r1");
        assertEquals(1, reservationsList.size(),"one new reservation");
        
        Reservation theReservation = reservationsList.get(0);

        assertEquals("r1", theReservation.getReservationId());
        assertEquals("c1", theReservation.getCustomer().getCustomerID());

    }

    public void availableTables() {

    }
    // @Test
    // public void tableNotAvailableException(){
    // assertThrows(TableNotAvailableException.class, () ->
    // service.addReservation(new Reservation("1", "1", "123", LocalDate.now(),
    // LocalTime.of(18, 0))));
    // }

    // @Test
    // public void customerNotFoundException(){
    // assertThrows(CustomerNotFoundException.class, () ->
    // service.allReservationsForCustomer("Appa"));
    // }

}
