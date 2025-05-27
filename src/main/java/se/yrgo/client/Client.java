package se.yrgo.client;

import se.yrgo.services.BookingService;
import se.yrgo.services.CustomerService;
import se.yrgo.services.ReservationService;
import se.yrgo.services.TableService;
import se.yrgo.data.ReservationNotAvailable;
import se.yrgo.data.TableNotAvailableException;
import se.yrgo.domain.*;
import se.yrgo.domain.DiningTable;
import se.yrgo.domain.Reservation;

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

    public static void introduction(Scanner input, BookingService service) throws TableNotAvailableException, ReservationNotAvailable{
        System.out.println("--- Welcome to Vapiano ---");
        System.out.println("[1] Make a reservation");
        System.out.println("[2] Find your reservation");
        System.out.println("[3] Change your reservation");
        System.out.println("[4] Cancel reservation");
        System.out.println("[5] Exit");
        navigation(input, service);
    }

    public static void navigation(Scanner input, BookingService service) throws TableNotAvailableException, ReservationNotAvailable{
        int option = input.nextInt();
        input.nextLine();

        switch (option) {
            case 1:
                create(input, service);
                introduction(input, service);
                break;
            case 2:
                find(input, service);
                introduction(input, service);
                break;
            case 3:
                System.out.println("What's your reservation number");
                String reservationID = input.nextLine();
                change(input, service, reservationID);
                introduction(input, service);
                break;
            case 4:
                System.out.println("What's your reservation number");
                String resId = input.nextLine();
                cancel(input, service, resId);
                introduction(input, service);
                break;
            case 5:
                System.out.println("Goodbye");
                break;
            default:
                System.out.println("Wrong input \nGoodbye");
                break;
        }
    }

     public static void create(Scanner input, BookingService service) throws TableNotAvailableException, ReservationNotAvailable  {
        System.out.println("--- Make a reservation ---");
        System.out.println("Which day would you like to dine with us? (2000-01-01)");
        String date = input.nextLine();
        String time = timePicker(input);
        
        System.out.println("\nHow large will your party be?");
        int amountOfPeople = input.nextInt();
        input.nextLine();
        
        System.out.println("\nEnter your first and lastname");
        String fullName = input.nextLine();

        System.out.println("\nEnter your email");
        String email = input.nextLine();
        
        System.out.println("\nEnter your phone number");
        String number = input.nextLine();
        
        System.out.println("- Your Reservation -");
        System.out.println("\n\tDate: " + date +
                        "\n\tTime: " + time + 
                        "\n\tName: " + fullName +
                        "\n\tEmail: " + email + 
                        "\n\tNumber: " + number);
        
        System.out.println("- Confirm/Cancel -\n");
        String confirm = input.nextLine();

        switch (confirm) {
            case "confirm":
                service.makeReservation(date, time, amountOfPeople, fullName, email, number);
                System.out.println("Welcome to Vapiano the " + date + " at " + time);
                introduction(input, service);
                break;
            case "cancel":
                System.out.println("Canceling reservation");
                introduction(input, service);
            default:
                break;
        }
    }

    public static String timePicker(Scanner input) {
        System.out.println("\nAt what time would you like to dine?");
        System.out.println("[1] 16:00\n" +
                "[2] 18:00\n" +
                "[3] 20:00\n" +
                "[4] 22:00");
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
                System.out.println("Please, pick one of the following: ");
                break;
        }
        return time;
    }

    private static void find(Scanner input, BookingService service) {
        System.out.println("--- Find Reservation ---");
        System.out.println("Enter your reservation ID");
        String reservationID = input.nextLine();
        Reservation result = service.findReservation(reservationID);
        System.out.println("You have the following reservation:\n");
        System.out.println(result.info());

         System.out.println("[1] Change reservation\n" +
                            "[2] Remove reservation\n");
        int option = input.nextInt();
        switch (option) {
            case 1:
                change(input, service, result.getReservationId());
                break;
            case 2:
                cancel(input, service, result.getReservationId());
                break;
            default:
                break;
        }
    }

    public static void change(Scanner input, BookingService service, String id) {
        System.out.println("Choose a date (2000-02-22)");
        String date = input.nextLine();

        System.out.println("What time");
        String time = input.nextLine();

        service.updateReservation(id, date, time); 
    }

    public static void cancel(Scanner input, BookingService service, String id) {
        Reservation result = service.findReservation(id);
        System.out.println(result);
        System.out.println("Would you like to cancel this reservation?");
        String answer = input.nextLine();
        switch (answer) {
            case "yes":
                service.deleteReservatuion(id);
                System.out.println("Reservation cancelled");
                break;
            case "no":
                System.out.println("We see you in " + result.getReservationDate().getMonth() + " the "
                        + result.getReservationDate().getDayOfMonth() + "th at " + result.getReservationTime());
            default:
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