package se.yrgo.client;

import se.yrgo.services.BookingService;
import se.yrgo.services.CustomerService;
import se.yrgo.services.ReservationService;
import se.yrgo.services.TableService;
import se.yrgo.data.TableNotAvailableException;
import se.yrgo.domain.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeParseException;

public class Client {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
        BookingService service = container.getBean("bookingService", BookingService.class);
        CustomerService customerService = container.getBean("customerService", CustomerService.class);
        TableService tableService = container.getBean("tableService", TableService.class);
        ReservationService reservationService = container.getBean("reservationService", ReservationService.class);

        try {
            setUp(customerService, tableService, reservationService);

            try (Scanner input = new Scanner(System.in)) {
                introduction(input, service);
            }
        } catch (TableNotAvailableException e) {
            System.out.println(e);
        } finally {
            container.close();
        }
    }

    public static void introduction(Scanner input, BookingService service) {
        System.out.println("Welcome to the Restaurant Booking system.");
        System.out.println("1. Make a reservation");
        System.out.println("2. Find your reservation");
        System.out.println("3. Cancel reservation");
        System.out.println("4. Exit");
        navigation(input, service);
    }

    public static void navigation(Scanner input, BookingService service) {
        int option = input.nextInt();
        input.nextLine();

        switch (option) {
            case 1:
                create(input, service);
                // service.makeReservation();
                break;

            case 2:
                find(input, service);
                introduction(input, service);
                break;

            case 3:
                cancel(input, service);
                introduction(input, service);
                break;

            case 4:
                System.out.println("Goodbye");
                break;
            default:
                System.out.println("Wrong input \nGoodbye");
                break;
        }
    }

    private static void find(Scanner input, BookingService service) {
        System.out.println("What's your reservation number");
        String reservationID = input.nextLine();
        Reservation result = service.findReservation(reservationID);
        System.out.println(result.info());
    }

    public static void cancel(Scanner input, BookingService service) {
        System.out.println("What's your reservation number");
        String reservationID = input.nextLine();
        Reservation result = service.findReservation(reservationID);
        System.out.println(result);
        System.out.println("Would you like to cancel this reservation?");
        String answer = input.nextLine();
        switch (answer) {
            case "yes":
                service.deleteReservatuion(reservationID);
                System.out.println("Reservation cancelled");
                break;
            case "no":
                System.out.println("We see you in " + result.getReservationDate().getMonth() + " the "
                        + result.getReservationDate().getDayOfMonth() + "th at " + result.getReservationTime());
            default:
                break;
        }

    }

    public static void create(Scanner input, BookingService service) {
        System.out.println("Who is the customer? Give the customerId:");
        String customerId = input.nextLine(); // Läs hela raden direkt

        // Tid och Datum för bokningen
        LocalDate lD = null;
        while (lD == null) {
            System.out.println("Enter the date for the reservation (YYYY-MM-DD):");
            try {
                lD = LocalDate.parse(input.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Try again.");
            }
        }

        LocalTime lT = null;
        while (lT == null) {
            System.out.println("Enter the time for the reservation (HH:MM):");
            try {
                lT = LocalTime.parse(input.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Try again.");
            }
        }

        // Bekräfta bokningsinfo
        System.out.println("\nReservation details:");
        System.out.println("Customer ID: " + customerId);
        System.out.println("Date: " + lD);
        System.out.println("Time: " + lT);
        System.out.println("Are you happy with this? (yes/no)");

        String confirm = input.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            service.makeReservation(customerId, lD, lT); // Om din metod kräver reservationId, säg till
            System.out.println("Reservation created.");
        } else {
            System.out.println("Reservation cancelled.");
        }
    }



    // // Bekräfta
    // System.out.println("Reservation details:");
    // System.out.println("Customer ID: " + customerId);
    // System.out.println("Date: " + lD);
    // System.out.println("Time: " + lT);
    // System.out.println("Are you happy with this? (yes/no)");

    public static void setUp(CustomerService customerService, TableService tableService,
            ReservationService reservationService) throws TableNotAvailableException {
        customerService.addCustomer(new Customer("123", "John Doe", "doe.john@gmail.com", "0707080908"));
        customerService.addCustomer(new Customer("124", "Anna Andersson", "anna@gmail.com", "0701234567"));
        customerService.addCustomer(new Customer("125", "Bertil Bengtsson", "bertil@gmail.com", "0709876543"));
        customerService.addCustomer(new Customer("126", "Cecilia Citron", "cecilia@gmail.com", "0706146846"));

        tableService.addTable(new DiningTable("1", 4, true));
        tableService.addTable(new DiningTable("2", 2, true));
        tableService.addTable(new DiningTable("3", 6, true));

        LocalTime.now();
        reservationService.addReservation(new Reservation("12345", tableService.getTable("1"),
                customerService.getCustomer("123"), LocalDate.now(), LocalTime.of(18, 0)));

        reservationService.addReservation(new Reservation("12346", tableService.getTable("2"),
                customerService.getCustomer("124"), LocalDate.now(), LocalTime.of(20, 0)));
    }
}