package se.yrgo.services;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public BookingServiceProductionImp(CustomerService cs, TableService ts, ReservationService rs) {
        this.customerService = cs;
        this.tableService = ts;
        this.reservationService = rs;
    }

    @Override
    public void makeReservation(String date, String time, int amoutOfSeats, String fullName, String email,
            String number) throws TableNotAvailableException, ReservationNotAvailable {
        try {
            checkForAvailableTimeAndDate(date, time);
            DiningTable tableToBook = checkForAvailableDiningTable(amoutOfSeats);
            Customer newCustomer = checkForAlreadyExcistingCustomer(fullName, email, number);
            reservationService.addReservation(
                    new Reservation("r4", tableToBook, newCustomer, LocalDate.parse(date), LocalTime.parse(time)));
        } catch (TableNotAvailableException e) {
            throw new TableNotAvailableException();
        } catch (ReservationNotAvailable ex) {
            throw new ReservationNotAvailable();
        }
    }

    public void checkForAvailableTimeAndDate(String date, String time) throws ReservationNotAvailable {
        List<Reservation> allReservations = reservationService.getAllReservations();
        for (Reservation r : allReservations) {
            if ((r.getReservationDate() == LocalDate.parse(date))
                    && (r.getReservationTime() == LocalTime.parse(time))) {
                throw new ReservationNotAvailable();
            }
        }
    }

    public DiningTable checkForAvailableDiningTable(int amoutOfSeats) throws TableNotAvailableException {
        List<DiningTable> tableList = tableService.getAllAvailableTables();
        for (DiningTable dt : tableList) {
            if (dt.getAmountOfSeats() >= amoutOfSeats) {
                return dt;
            }
        }
        throw new TableNotAvailableException();
    }

    public Customer checkForAlreadyExcistingCustomer(String fullName, String email, String number) {
        List<Customer> customers = customerService.getAllCustomers();
        for (Customer c : customers) {
            if ((fullName == c.getName()) && (email == c.getEmail())) {
                return c;
            }
        }
        Customer newCustomer = new Customer("14", fullName, email, number);
        customerService.addCustomer(newCustomer);
        return newCustomer;
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
    public void updateReservation(String reservationId, String date, String time) {
        Reservation result = reservationService.getReservation(reservationId);
        if (result != null) {
            result.setReservationDate(LocalDate.parse(date));
            result.setReservationTime(LocalTime.parse(time));
            reservationService.changeReservation(result);
        } else {
            throw new IllegalArgumentException("Reservation not found with ID: " + reservationId);
        }
    }

}
