package se.yrgo.client;

import se.yrgo.services.BookingService;
import se.yrgo.data.CustomerNotFoundException;
import se.yrgo.data.TableNotAvailableException;
import se.yrgo.domain.*;
import se.yrgo.domain.Table;

import javax.persistence.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;
import java.time.*; 

public class Client { 
    public static void main(String[] args) {
        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
        BookingService service = container.getBean("bookingService", BookingService.class);
        
        service.addCustomer(new Customer("123", "John Doe", "doe.john@gmail.com", "0707080908"));
        service.addCustomer(new Customer("124", "Anna Andersson", "anna@gmail.com", "0701234567"));
        service.addCustomer(new Customer("125", "Bertil Bengtsson", "bertil@gmail.com", "0709876543"));
        service.addCustomer(new Customer("126", "Cecilia Citron", "cecilia@gmail.com", "0706146846"));
    
        service.addTable(new Table(1, 4, false));
        //service.addTable(new Table(2, 2, true));
        //service.addTable(new Table(3, 6, true));
        
        
        try {
            //This should work
            service.addReservation(new Reservation("r1", "1", "124", LocalDate.now().plusDays(1), LocalTime.of(18, 0)));
            System.out.println(service.allReservationsForCustomer("124"));
            System.out.println("hej");
            //This is suppose to not work
            System.out.println(service.allReservationsForCustomer("1"));

        } 
        catch (TableNotAvailableException t) {
            System.out.println(t.getMessage());
        } 
        catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        }

        container.close(); 
    }

    public void introduction() {
        System.out.println("Welcome to the Restaurant Booking system.");
        System.out.println("If you want to quit this program type 'close'.");
        System.out.println("For more information type 'info' \n");
    }

    public static void setUp(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        /*
         * Table t1 = Table(1, 4, false);
         * tables.put(2, new Table(2, 2, true));
         * tables.put(3, new Table(3, 6, true));
         * 
         * //Adding some customers
         * customers.put("1", new Customer("1", "Anna Andersson", "anna@gmail.com",
         * "0701234567"));
         * customers.put("2", new Customer("2","Bertil Bengtsson", "bertil@gmail.com",
         * "0709876543"));
         * // customers.put("3", new Customer("3","Cecilia Citron", "cecilia@gmail.com",
         * "0706146846"));
         * 
         * //Adding some bookings
         * reservations.put("r1", new Reservation(
         * "r1", "1", "1",
         * LocalDate.now().plusDays(1),
         * LocalTime.of(18, 0)
         * ));
         */
        tx.commit();
        em.close();
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