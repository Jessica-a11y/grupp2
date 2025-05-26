package se.yrgo.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import org.hibernate.transform.ToListResultTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.data.*;
import se.yrgo.domain.*;

@Service("bookingService")
@Transactional
public class BookingServiceProductionImp implements BookingService {
    private CustomerService customerService;
    private TableService tableService;
    private ReservationService reservationService;

    public BookingServiceProductionImp(CustomerService cs, TableService ts, ReservationService rs){
        this.customerService = cs;
        this. tableService = ts;
        this.reservationService = rs;
    }

    @Override
    public void makeReservation(String date, String time, int amoutOfSeats, String fullName, String email, String number) {
        System.out.println("Maybe we will make one");
        List<Reservation> allReservations = reservationService.getAllReservations();
        for(Reservation r : allReservations) {
            if(r.getReservationDate() == LocalDate.parse(date) && r.getReservationTime() == LocalTime.parse(time)) {
                System.out.println("Sorry, no table is available at this date and time");
                break;
            } 
        }
        DiningTable tableToBook = new DiningTable();
        List<DiningTable> tableList = tableService.getAllAvailableTables();
        for(DiningTable dt : tableList) {
            if(dt.getAmountOfSeats() == amoutOfSeats) {
                tableToBook = dt;
                break;
            }
        }

        Customer newCustomer = new Customer();
        List<Customer> customers = customerService.getAllCustomers();
        for(Customer c : customers) {
            if(newCustomer.getName() != c.getName() && newCustomer.getEmail() != c.getEmail()) {
                newCustomer = new Customer("12347", fullName, email, number);
            }
            else {
                newCustomer = c;
                break;
            }
        }
        customerService.addCustomer(newCustomer);
        

       /*  Customer customer = customerService.getCustomer(customerId);
        List<DiningTable> tableList = tableService.getAllAvailableTables();
        
        DiningTable table = tableList.get(0);

        List<Reservation> allReservations = reservationService.getAllReservations();

        tableService.noLongerAvailable(table.getTableNumber()); */


        /*   Kontrollera att bordet är ledigt vid önskat datum/tid.
            Kontrollera om kunden finns, annars skapa en ny kund.
            Skapa reservation och länka till kund och bord.
            Spara reservationen. */
        
        try {
            reservationService.addReservation(new Reservation("r4", tableToBook, newCustomer, LocalDate.parse(date), LocalTime.parse(time)));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void deleteReservatuion(String reservationId) {
        reservationService.removeReservation(reservationId);
    }

    @Override
    public Reservation findReservation(String reservationID) {
        return reservationService.getReservation(reservationID);
    }

    @Override
    public void availableTables() {
        System.out.println("Avaliable tables");
    }

    @Override
    public void updateReservation(Reservation changedReservation) {
        reservationService.changeReservation(changedReservation);
    }
    

}
