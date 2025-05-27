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
        Customer newCustomer = new Customer("C1", "John Doe", "doe.john@gmail.com", "0707080908");
        DiningTable newTable = new DiningTable("T1", 4, true);
        customerService.addCustomer(newCustomer);
        tableService.addTable(newTable);

        assertEquals(0, reservationService.getAllReservations().size());

        // This must be a valid soulution
        try {
            service.makeReservation("2025-05-30", "18:30", 4, "Daniel Gran", "denGryme@gamil.com", "0000000000");
            System.out.println(reservationService.getAllReservations().size());
        } catch (Exception e) {
            // TODO: handle exception
        }

        assertEquals(1, reservationService.getAllReservations().size());

        assertThrows(TableNotAvailableException.class,
                () -> service.makeReservation("2025-04-30", "18:30", 10, "Noel", "denGryme@gamil.com", "0000000000"));
    }

    @Test
    public void updateReservation() {
        Customer newCustomer = new Customer("C1", "John Doe", "doe.john@gmail.com", "0707080908");
        DiningTable newTable = new DiningTable("T1", 4, true);
        customerService.addCustomer(newCustomer);
        tableService.addTable(newTable);
        assertEquals(0, reservationService.getAllReservations().size());

        Reservation Reservation = new Reservation("R1", newTable, newCustomer, LocalDate.parse("2025-05-30"),
                LocalTime.parse("20:00"));
        try {
            reservationService.addReservation(Reservation);
        } catch (Exception e) {
            // TODO: handle exception
        }
        assertEquals(1, reservationService.getAllReservations().size());

        Reservation updatedReservation = new Reservation("R1", newTable, newCustomer, LocalDate.parse("2024-01-20"),
                LocalTime.parse("18:30"));
        service.updateReservation(updatedReservation);
        assertEquals(1, reservationService.getAllReservations().size());
    }

    @Test
    public void deleteReservatuion() {
        Customer newCustomer = new Customer("C1", "John Doe", "doe.john@gmail.com", "0707080908");
        DiningTable newTable = new DiningTable("T1", 4, true);
        Reservation newReservation = new Reservation("R1", newTable, newCustomer, LocalDate.parse("2024-01-20"),
                LocalTime.parse("18:30"));
        customerService.addCustomer(newCustomer);
        tableService.addTable(newTable);
        assertEquals(0, reservationService.getAllReservations().size());

        try {
            reservationService.addReservation(newReservation);
        } catch (Exception e) {
            // TODO: handle exception
        }
        assertEquals(1, reservationService.getAllReservations().size());

        // There is no reservation with a reservationId named R0
        service.deleteReservatuion("R0");
        assertEquals(1, reservationService.getAllReservations().size());

        service.deleteReservatuion("R1");
        assertEquals(0, reservationService.getAllReservations().size());
    }

    @Test
    public void findReservation() {
        Customer customer = new Customer("C1", "John Doe", "doe.john@gmail.com","0707080908");
        DiningTable table = new DiningTable("T1", 4, true);
        Reservation reservation = new Reservation("R1", table, customer, LocalDate.now(), LocalTime.of(18, 0));
        assertEquals(0, reservationService.getAllReservations().size(), "there should be no new reservation");

        customerService.addCustomer(customer);
        tableService.addTable(table);
        try {
            reservationService.addReservation(reservation);
        } catch (Exception e) {
            // TODO: handle exception
        }
        assertEquals(1, reservationService.getAllReservations().size(), "there should be 1 new reservation");


       
        Reservation foundReservation = service.findReservation("R1");
        

        assertEquals("R1", foundReservation.getReservationId());
        assertEquals("T1", foundReservation.getTable().getTableNumber());
        assertEquals("C1", foundReservation.getCustomer().getCustomerID());
    }

    //This is not implementet yet, but this function excits in the TableService
    @Test
    public void availableTables() {
        DiningTable table1 = new DiningTable("T1", 3, true);
        DiningTable table2 = new DiningTable("T2", 4, false);
        DiningTable table3 = new DiningTable("T3", 6, true);
        DiningTable table4 = new DiningTable("T4", 8, true);
        DiningTable table5 = new DiningTable("T5", 9, false);

        tableService.addTable(table1);
        tableService.addTable(table2);
        tableService.addTable(table3);
        tableService.addTable(table4);
        tableService.addTable(table5);

        assertEquals(3, tableService.getAllAvailableTables().size());
    }
}
