package se.yrgo.client;

import se.yrgo.services.BookingService;
import se.yrgo.services.CustomerService;
import se.yrgo.services.ReservationService;
import se.yrgo.services.TableService;
import se.yrgo.data.ReservationNotAvailable;
import se.yrgo.data.TableNotAvailableException;
import se.yrgo.domain.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;
import java.time.*;

public class Client {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml"); //Här
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
        } catch(ReservationNotAvailable ex) {
            System.out.println(ex);
        }finally {
            container.close();
        }
    }

    public static void introduction(Scanner input, BookingService service) throws TableNotAvailableException, ReservationNotAvailable {
        while (true) {
            System.out.println("\n--- Welcome to Vapiano ---");
            System.out.println("[1] Make a reservation");
            System.out.println("[2] Find your reservation");
            System.out.println("[3] Change your reservation");
            System.out.println("[4] Cancel reservation");
            System.out.println("[5] Exit");

            int option = navigation(input);
            switch (option) {
                case 1:
                    create(input, service);
                    break;
                case 2:
                    find(input, service);
                    break;
                case 3:
                    System.out.println("What's your reservation number");
                    String reservationID = input.nextLine();
                    change(input, service, reservationID);
                    break;
                case 4:
                    System.out.println("What's your reservation number");
                    String resId = input.nextLine();
                    cancel(input, service, resId);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return; 
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public static int navigation(Scanner input) {
        if (!input.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            input.nextLine();
            return -1;
        }
        int option = input.nextInt();
        input.nextLine(); 
        return option;
    }

    public static void create(Scanner input, BookingService service) throws TableNotAvailableException, ReservationNotAvailable {
        System.out.println("--- Make a Reservation ---");

        System.out.println("What date would you like to book? (e.g., 2025-12-31)");
        String date = input.nextLine();

        String time = timePicker(input);

        System.out.println("\nHow many people in your party?");
        int amountOfPeople = input.nextInt();
        input.nextLine();

        System.out.println("\nEnter your full name:");
        String fullName = input.nextLine();

        System.out.println("\nEnter your email:");
        String email = input.nextLine();

        System.out.println("\nEnter your phone number:");
        String number = input.nextLine();

        System.out.println("\n--- Reservation Summary ---");
        System.out.println("\tDate: " + date);
        System.out.println("\tTime: " + time);
        System.out.println("\tName: " + fullName);
        System.out.println("\tEmail: " + email);
        System.out.println("\tPhone: " + number);

        System.out.println("\nType 'confirm' to confirm or 'cancel' to cancel:");

        while (true) {
            String confirm = input.nextLine().trim().toLowerCase();
            if (confirm.equals("confirm")) {

                //Såhär
                service.makeReservation(date, time, amountOfPeople, fullName, email, number);
                System.out.println("Reservation confirmed! See you on " + date + " at " + time + ".");
                
                //Detta var det jag skrev men vi kan ta bort det 
                //Reservaton newmadeReservation = service.makeReservation(date, time, amountOfPeople, fullName, email, number);
                //System.out.println("Welcome to Vapiano the " + date + " at " + time);
                //System.out.println("Your reservation ID is: " + newmadeReservation.getReservationId());
                break;
            } else if (confirm.equals("cancel")) {
                System.out.println("Reservation cancelled.");
                break;
            } else {
                System.out.println("Invalid input. Please type 'confirm' or 'cancel'.");
            }
        }
    }

    public static String timePicker(Scanner input) {
        while (true) {
            System.out.println("\nAt what time would you like to dine?");
            System.out.println("[1] 16:00\n[2] 18:00\n[3] 20:00\n[4] 22:00");

            if (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
                continue;
            }

            int option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1: return "16:00";
                case 2: return "18:00";
                case 3: return "20:00";
                case 4: return "22:00";
                default:
                    System.out.println("Please choose one of the listed options.");
            }
        }
    }

    public static void find(Scanner input, BookingService service) {
        System.out.println("--- Find a Reservation ---");
        System.out.println("Enter your email:");
        String customerEmail = input.nextLine();

        Reservation result = service.findReservation(customerEmail);
        if (result == null) {
            System.out.println("No reservation found with that email.");
            return;
        }

        System.out.println("Reservation Details:\n" + result.info());
        System.out.println("\n[1] Change reservation\n[2] Cancel reservation");

        if (!input.hasNextInt()) {
            System.out.println("Invalid input. Returning to main menu.");
            input.nextLine();
            return;
        }

        int option = input.nextInt();
        input.nextLine();

        switch (option) {
            case 1:
                change(input, service, result.getReservationId());
                break;
            case 2:
                cancel(input, service, result.getReservationId());
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    public static void change(Scanner input, BookingService service, String id) {
        System.out.println("--- Change Reservation ---");

        System.out.println("Enter new date (e.g., 2025-12-31):");
        String date = input.nextLine();

        System.out.println("Enter new time (e.g., 18:00):");
        String time = input.nextLine();

        service.updateReservation(id, date, time);
        System.out.println("Your reservation has been updated.");
    }

    public static void cancel(Scanner input, BookingService service, String id) {
        Reservation result = service.findReservation(id);
        if (result == null) {
            System.out.println("No reservation found with that ID.");
            return;
        }

        System.out.println("--- Reservation ---");
        System.out.println(result.info());

        System.out.println("Do you want to cancel this reservation? (yes/no)");
        String answer = input.nextLine().trim().toLowerCase();

        switch (answer) {
            case "yes":
                service.deleteReservatuion(id);
                System.out.println("Reservation cancelled.");
                break;
            case "no":
                System.out.println("Reservation remains active.");
                break;
            default:
                System.out.println("Invalid response. No changes made.");
                break;
        }
    }

    public static void setUp(CustomerService customerService, TableService tableService,
            ReservationService reservationService) throws TableNotAvailableException {
        customerService.addCustomer(new Customer("530", "John Doe", "doe.john@gmail.com", "0707080908"));
        customerService.addCustomer(new Customer("531", "Anna Andersson", "anna@gmail.com", "0701234567"));
        customerService.addCustomer(new Customer("532", "Bertil Bengtsson", "bertil@gmail.com", "0709876543"));
        customerService.addCustomer(new Customer("533", "Cecilia Citron", "cecilia@gmail.com", "0706146846"));

        tableService.addTable(new DiningTable("1", 4, true));
        tableService.addTable(new DiningTable("2", 2, true));
        tableService.addTable(new DiningTable("3", 6, true));

        LocalTime.now();
        reservationService.addReservation(new Reservation("12345", tableService.getTable("1"),
                customerService.getCustomer("530"), LocalDate.now(), LocalTime.of(18, 0)));

        reservationService.addReservation(new Reservation("12346", tableService.getTable("2"),
                customerService.getCustomer("531"), LocalDate.now(), LocalTime.of(20, 0)));
    }
}