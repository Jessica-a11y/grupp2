package se.yrgo.client;

import se.yrgo.mock.MockBookingService;
import se.yrgo.services.BookingService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import javax.persistence.*;
import javax.security.auth.Subject;

import se.yrgo.domain.*;

public class Client {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        BookingService service = new MockBookingService(); 
        /* List<Reservation> reservations = service.allReservationsForCustomer("1"); 
        for (Reservation r : reservations) {
            System.out.println(r.toString());
        } */

        service.addReservation(new Reservation("4", "3", "1", LocalDate.now().plusDays(3), LocalTime.of(21, 0)));
        List<Reservation> res = service.getAllReservations();
        for(Reservation c : res) {
            System.out.println(c);
        }

        
    } 

    public void introduction(){
        System.out.println("Welcome to the Restaurant Booking system.");
        System.out.println("If you want to quit this program type 'close'.");
        System.out.println("For more information type 'info' \n");
    }

    public static void setUp(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();



        Table t1 = Table(1, 4, false));
        tables.put(2, new Table(2, 2, true));
        tables.put(3, new Table(3, 6, true));

        //Adding some customers
        customers.put("1", new Customer("1", "Anna Andersson", "anna@gmail.com", "0701234567"));
        customers.put("2", new Customer("2","Bertil Bengtsson", "bertil@gmail.com", "0709876543"));
        // customers.put("3", new Customer("3","Cecilia Citron", "cecilia@gmail.com", "0706146846"));

        //Adding some bookings
        reservations.put("r1", new Reservation(
            "r1", "1", "1",
            LocalDate.now().plusDays(1),
            LocalTime.of(18, 0)
        ));

        tx.commit();
        em.close();
    }


    public void info(){
        System.out.println(" " +
                           " "
        );
        
    }

    public void navigation(String action){
        
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