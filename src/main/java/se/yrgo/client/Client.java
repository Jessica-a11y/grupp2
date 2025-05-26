package se.yrgo.client;

import se.yrgo.services.BookingService;
import se.yrgo.services.CustomerService;
import se.yrgo.services.TableService;
import se.yrgo.data.TableNotAvailableException;
import se.yrgo.domain.*;
import se.yrgo.domain.Table;
import se.yrgo.domain.Reservation;

import javax.persistence.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;
import java.time.*; 

public class Client { 
    public static void main(String[] args) {
        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
        BookingService service = container.getBean("bookingService", BookingService.class);
        CustomerService customerService = container.getBean("customerService", CustomerService.class);
        TableService tableService = container.getBean("tableService", TableService.class);
        
        try {
            setUp(customerService, tableService);

            try(Scanner input = new Scanner(System.in)){
                introduction(input, service);
            }
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
        int option = input.nextInt();
        navigation(option, service); 
    }

    public static void navigation(int action, BookingService service) {

        switch (action) {
            case 1:
                service.makeReservation(new Customer(null, null, null, null));
                break;

            case 2:
                service.findReservation();
                break;

            case 3:
                service.deleteReservatuion();
                break;

            case 4:
                
                break;
            default:
                break;
        }
    }

    public static void setUp(CustomerService customerService, TableService tableService) {
        customerService.addCustomer(new Customer("123", "John Doe", "doe.john@gmail.com", "0707080908"));
        customerService.addCustomer(new Customer("124", "Anna Andersson", "anna@gmail.com", "0701234567"));
        customerService.addCustomer(new Customer("125", "Bertil Bengtsson", "bertil@gmail.com", "0709876543"));
        customerService.addCustomer(new Customer("126", "Cecilia Citron", "cecilia@gmail.com", "0706146846")); 
        
        tableService.addTable(new Table("1", 4, true));
        tableService.addTable(new Table("2", 2, true));
        tableService.addTable(new Table("3", 6, true)); 

        
    }

    public void info() {
        System.out.println(" " +
                " ");

    }

    

}