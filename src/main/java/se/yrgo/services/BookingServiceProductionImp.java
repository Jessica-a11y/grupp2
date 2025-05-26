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

    public BookingServiceProductionImp(CustomerService cs, TableService ts, ReservationService rs){
        this.customerService = cs;
        this. tableService = ts;
        this.reservationService = rs;
    }

    @Override
    public void makeReservation(String customerId, LocalDate lD, LocalTime lT) {
        System.out.println("Maybe we will make one");

        Customer customer = customerService.getCustomer(customerId);
        List<DiningTable> tableList = tableService.getAllAvailableTables();
        
        DiningTable table = tableList.get(0);

        List<Reservation> allReservations = reservationService.getAllReservations();

        tableService.noLongerAvailable(table.getTableNumber());


        /*   Kontrollera att bordet är ledigt vid önskat datum/tid.
            Kontrollera om kunden finns, annars skapa en ny kund.
            Skapa reservation och länka till kund och bord.
            Spara reservationen. */
        
        Reservation newReservation = new Reservation();
        
        try {
            reservationService.addReservation(newReservation);
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
