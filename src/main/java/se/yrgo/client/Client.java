package se.yrgo.client;

import se.yrgo.mock.MockBookingService;
import se.yrgo.services.BookingService;
import java.util.*;
import se.yrgo.domain.*;

public class Client {
    public static void main(String[] args) {
        BookingService service = new MockBookingService(); 
        List<Table> tables = service.allReservatedTablesForCustomer("1"); 

        
    } 
} 