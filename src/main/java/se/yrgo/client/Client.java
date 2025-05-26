package se.yrgo.client;

import se.yrgo.services.BookingService;
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
        setUp(service);

        //Reservation reservation = new Reservation("r1", "t3", "124", LocalDate.now(), LocalTime.of(18, 0));
        
        try {
            setUp(service);
            LocalTime.now();

            Customer c1 = new Customer("1", "Lena", "lena@example.com", "0708763452");
            Table t1 = new Table("4", 4, true);

            // service.addReservation(new Reservation("9900", t1, c1, LocalDate.now(), LocalTime.of(18, 0)));
            System.out.println("steg 2 \n\n" );
            //service.updateReservation(reservation);
            
            for (Reservation r : service.getAllReservations()) {
                System.out.println(r.toString());
            }
        } 
        // catch(TableNotAvailableException e) {
        //     System.out.println("Sorry, no table avaliable at this date and time.");
        // } 
        finally {
            container.close(); 
        }


        // try {
        //     setUp(service);
        //     LocalTime.now();
        //     service.addReservation(new Reservation("9900", "1", "123", LocalDate.now(), LocalTime.of(18, 0)));
        // } catch(TableNotAvailableException e) {
        //     System.out.println("Sorry, no table avaliable at this date and time.");
        // } finally {
        //     container.close(); 
        // }
        
        
    }

    public void introduction() {
        System.out.println("Welcome to the Restaurant Booking system.");
        System.out.println("If you want to quit this program type 'close'.");
        System.out.println("For more information type 'info' \n");
    }

    public static void setUp(BookingService service) {
        service.addCustomer(new Customer("123", "John Doe", "doe.john@gmail.com", "0707080908"));
        service.addCustomer(new Customer("124", "Anna Andersson", "anna@gmail.com", "0701234567"));
        service.addCustomer(new Customer("125", "Bertil Bengtsson", "bertil@gmail.com", "0709876543"));
        service.addCustomer(new Customer("126", "Cecilia Citron", "cecilia@gmail.com", "0706146846")); 
        
        service.addTable(new Table("t1", 4, true));
        service.addTable(new Table("t2", 2, true));
        service.addTable(new Table("t3", 6, true)); 
        // try{
        //     service.addReservation(new Reservation("r1", "t1", "123", LocalDate.now(), LocalTime.of(18, 0)));
        //     service.addReservation(new Reservation("r2", "t2", "126", LocalDate.now(), LocalTime.of(18, 0)));
        // }catch(TableNotAvailableException e) {
        //     System.out.println("Sorry, no table avaliable at this date and time.");
        // }

    }

    public void info() {
        System.out.println(" " +
                " ");

    }

    public void navigation(String action) {

        switch (action.toLowerCase()) {
            case "find customer":

                break;

            case "find reservation":

                break;
            case "find table":

                break;
            default:
                break;
        }
    }

}