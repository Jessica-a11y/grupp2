package se.yrgo.domain;

import java.time.*;

import javax.persistence.Entity;
/**
 * Author Daniel Grahn
 */
@Entity
public class Reservation{
    private String reservationId;
    private String tableId;
    private String customerId;

    private LocalDate reservationDate;
    private LocalTime reservationTime;

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
