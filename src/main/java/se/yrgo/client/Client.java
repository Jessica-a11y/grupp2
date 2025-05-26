package se.yrgo.client;

import se.yrgo.services.BookingService;
import se.yrgo.services.CustomerService;
import se.yrgo.services.ReservationService;
import se.yrgo.services.TableService;
import se.yrgo.data.TableNotAvailableException;
import se.yrgo.domain.*;
import se.yrgo.domain.DiningTable;
import se.yrgo.domain.Reservation;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;
import java.time.*;

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
                System.out.println("We see you in " + result.getReservationDate().getMonth() + " the " + result.getReservationDate().getDayOfMonth() + "th at " + result.getReservationTime());
            default:
                break;
        }
        
    }
    

    public static void create(Scanner input, BookingService service) {
        System.out.println("What day would you like to book?");
        String date = input.nextLine();
        String time = timePicker(input);
        System.out.println("Amount of people");
        int amountOfPeople = input.nextInt();
        input.nextLine();
        System.out.println("First and lastname");
        String fullName = input.nextLine(); 
        System.out.println("Email");
        String email = input.nextLine();
        System.out.println("Phone number");
        String number = input.nextLine();
        List<String> strings = List.of(date, time, fullName, email, number);
        for(String s : strings) {
            System.out.println(s);
        }

        service.makeReservation(date, time, amountOfPeople, fullName, email, number);
       
        //Vem är person vi söker 

        // vilket bord ska personen ha (id)

        // Tid och Datum för bokningen

        //Generera ett reservationID som inte finns än

        //är du nöjd?

        //skicka
        
    }

    public static String timePicker(Scanner input) {
        System.out.println("At what time?");
        System.out.println("1. 16:00\n" + 
                            "2. 18:00\n" + 
                            "3. 20:00\n" + 
                            "4. 22:00");
        int option = input.nextInt();
        input.nextLine();
        String time = "";
        switch (option) {
            case 1:
                time = "16:00";
                break;
            case 2:
                time = "18:00";
                break;
            case 3:
                time = "20:00";
                break;
            case 4:
                time = "22:00";
                break;
            default:
                System.out.println("try again");
                break;
        }
        return time;
    }

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