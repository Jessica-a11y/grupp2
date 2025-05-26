package se.yrgo.services;

import java.util.*;

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
    public void makeReservation(Customer customer) {
        Table tableToBook;
        List<Table> tables = tableService.getAllAvailableTables();
        for(Table t : tables) {
            if(t.getAmountOfSeats() >= 5){
                tableToBook = t;
                break;
            }
        } 
       

        /*   Kontrollera att bordet är ledigt vid önskat datum/tid.
            Kontrollera om kunden finns, annars skapa en ny kund.
            Skapa reservation och länka till kund och bord.
            Spara reservationen. */
        
    }

    @Override
    public void changeReservation() {
        
    }

    @Override
    public void deleteReservatuion() {
        /* Ta bort eller markera bokningen som avbokad. */
    }

    @Override
    public void findReservation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findReservation'");
    }

    @Override
    public void availableTables() {
         // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findReservation'");
    }

    @Override
    public void updateReservation(Reservation changedReservation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateReservation'");
    }
    

}
