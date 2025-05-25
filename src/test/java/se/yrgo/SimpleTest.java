package se.yrgo;

import se.yrgo.services.*;
import se.yrgo.data.*;
import se.yrgo.domain.*;
import se.yrgo.domain.Table;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.*;

import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SimpleTest {
    ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
    BookingService service = container.getBean("bookingService", BookingService.class);
   
   
    @Test
    public void addCustomer() {
        service.addCustomer(new Customer("123", "John Doe", "doe.john@gmail.com", "0707080908"));
        ArrayList<Customer> customerList = (ArrayList)service.getAllCustomers();
        Customer theCustomer = customerList.get(0);

        assertEquals(theCustomer.getCustomerID(), "123", "Check if right id");
        assertEquals(theCustomer.getName(), "John Doe", "Check if the name is right");
    }

    @Test
    public void addTable() {
        Table newTable = new Table("1", 4, false);
        service.addTable(newTable);
    }




    @Test
    public void customerNotFoundException(){
        assertThrows(CustomerNotFoundException.class, () -> service.allReservationsForCustomer("Appa"));
    }
    
    @Test
    public void tableNotAvailableException(){
        assertThrows(TableNotAvailableException.class, () -> service.addReservation(new Reservation("1", "1", "123", LocalDate.now(), LocalTime.of(18, 0))));
    }

}
