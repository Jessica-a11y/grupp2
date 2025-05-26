package se.yrgo.services;

import java.time.*;
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
        // System.out.println("Maybe we will make one");
        /*   Kontrollera att bordet är ledigt vid önskat datum/tid.
            Kontrollera om kunden finns, annars skapa en ny kund.
            Skapa reservation och länka till kund och bord.
            Spara reservationen. */
        

        Customer customer = customerService.getCustomer(customerId);
        
        List<DiningTable> tableList = tableService.getAllAvailableTables();
        DiningTable table = tableList.get(0); //First table it can find
        tableService.noLongerAvailable(table.getTableNumber());

        List<Reservation> allReservations = reservationService.getAllReservations();
        List<String> allReservationId = new ArrayList<String>();
        for(Reservation res : allReservations){
            allReservationId.add(res.getReservationId());
        }
        String newGeneratedReservationId = generateNewId(allReservationId);

        Reservation newReservation = new Reservation(newGeneratedReservationId, table, customer, lD, lT);
        
        try {
            reservationService.addReservation(newReservation);
            System.out.println(newReservation.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        System.out.println("Updated! NOT!");
    }
    
    private static String generateNewId(List<String> existingIds) {
        Set<String> idSet = new HashSet<>(existingIds); // För snabb sökning
        long counter = 1;

        while (true) {
            String newId = "R" + counter;
            if (!idSet.contains(newId)) {
                return newId;
            }
            counter++;
        }
    }
}
