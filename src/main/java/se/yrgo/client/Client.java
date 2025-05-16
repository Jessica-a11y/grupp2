package se.yrgo.client;

import se.yrgo.mock.MockBookingService;
import se.yrgo.services.BookingService;
import java.util.*;
import se.yrgo.domain.*;

public class Client {
    public static void main(String[] args) {
        BookingService service = new MockBookingService(); 
        List<Reservation> reservations = service.allReservationsForCustomer("1"); 
        for (Reservation r : reservations) {
            System.out.println(r.toString());
        }

        
    } 
} 