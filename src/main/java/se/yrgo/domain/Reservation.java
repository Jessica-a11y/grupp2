package se.yrgo.domain;

import java.time.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Author Daniel Grahn
 */
@Entity
public class Reservation{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String reservationId;
    private String tableId;
    private String customerId;
    
    private LocalDate reservationDate;
    private LocalTime reservationTime;

    public Reservation() {}

    public Reservation(String reservationId, String tableId, String customerId, LocalDate reservationDate, LocalTime reservationTime) {
        this.reservationId = reservationId;
        this.tableId = tableId;
        this.customerId = customerId;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getTableId() {
        return tableId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public LocalTime getReservationTime() {
        return reservationTime;
    } 

    @Override
    public String toString(){ 
        return "Reservation ID: "+reservationId +" - Table ID: " + tableId + " - Customer ID: " + customerId + " - Reservation Date: " + reservationDate + " - Reservation Time: " + reservationTime;
    }
   
} 
