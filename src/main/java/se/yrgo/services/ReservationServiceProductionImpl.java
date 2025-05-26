package se.yrgo.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import se.yrgo.data.BookingDao;
import se.yrgo.data.CustomerNotFoundException;
import se.yrgo.data.TableNotAvailableException;
import se.yrgo.domain.Customer;
import se.yrgo.domain.Reservation;

public class ReservationServiceProductionImpl implements ReservationService{
    private BookingDao dao;

    public ReservationServiceProductionImpl(BookingDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(rollbackFor = TableNotAvailableException.class)
    public void addReservation(Reservation reservation) throws TableNotAvailableException {
        dao.createReservation(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return dao.allReservations();
    }

    @Override
    public List<Reservation> allReservationsForTable(String tableId) {
        return dao.allReservationsForTable(tableId);
    }

    @Override
    public List<Reservation> allReservationsForCustomer(String customerID) throws CustomerNotFoundException {
        //Before geting the list of reservations, we have to know if the customer excist.
        List<Customer> allCustomers = dao.allCustomers();

        for (Customer customer : allCustomers) {
            //System.out.println(customer.getName() + " " + customer.getCustomerID());
            if(customer.getCustomerID().equals(customerID)){
                return  dao.allReservationsForCustomer(customerID);
            }
        }

        throw new CustomerNotFoundException();
    }

    
    
}
