package se.yrgo.client;

import se.yrgo.mock.MockBookingService;
import se.yrgo.services.BookingService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import se.yrgo.domain.*;

public class Client {
    public static void main(String[] args) {
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
        System.out.println("Welcome to the Restaurant Booking system");
        System.out.println("for more ");
    }

    public void info(){
        
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