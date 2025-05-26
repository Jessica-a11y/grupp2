package se.yrgo.services;

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

    public BookingServiceProductionImp(CustomerService cs, TableService ts){
        this.customerService = cs;
        this. tableService = ts;
    }

    @Override
    public void makeReservation() {
        System.out.println("Maybe we will make one");
       

        /*   Kontrollera att bordet är ledigt vid önskat datum/tid.
            Kontrollera om kunden finns, annars skapa en ny kund.
            Skapa reservation och länka till kund och bord.
            Spara reservationen. */
        
    }

    @Override
    public void deleteReservatuion() {
        System.out.println("We removed your reservation");
    }

    @Override
    public void findReservation() {
        System.out.println("Did we find it?");
    }

    @Override
    public void availableTables() {
        System.out.println("Avaliable tables");
    }

    @Override
    public void updateReservation(Reservation changedReservation) {
        System.out.println("Updated! NOT!");
    }
    

}
