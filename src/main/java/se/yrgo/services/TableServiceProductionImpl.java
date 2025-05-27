package se.yrgo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.data.BookingDao;
import se.yrgo.domain.DiningTable;

/**
 * Production implementation of the {@link TableService} interface.
 * 
 * <p>
 * This service manages dining tables by delegating operations to the underlying {@link BookingDao}.
 * </p>
 * 
 * <p>
 * Annotated as a Spring {@code @Service} and marked as {@code @Transactional} to ensure transactional integrity
 * </p>
 * 
 * @author Daniel Grahn, Jessica Olofsson, for JavaDoc: Emilia Jarleback
 */

@Service("tableService")
@Transactional
public class TableServiceProductionImpl implements TableService{
    private BookingDao dao;

    /**
     * Constructs a new TableServiceProductionImpl with the specified BookingDao.
     * 
     * @param dao The {@link BookingDao} used for table persistence operations
     */
    public TableServiceProductionImpl(BookingDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Adds a new dining table by delegating to the DAO layer.
     * </p>
     * 
     * @param table The {@link DiningTable} to add
     */
    @Override
    public void addTable(DiningTable table) {
        dao.createTable(table);
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Retrieves a dining table by its unique ID from the DAO layer.
     * </p>
     * 
     * @param tableId The unique identifier of the tale
     * @return the {@link DiningTable} if found, otherwise {@code null}
     */
    @Override
    public DiningTable getTable(String tableId) {
        return dao.findTableById(tableId);
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Retrieves all dining tables from the DAO layer.
     * </p>
     * 
     * @return a list of all {@link DiningTable} objects
     */
    @Override
    public List<DiningTable> getAllTables() {
        return dao.allTables();
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Retrieves all avaliable dining tables from the DAO layer.
     * </p>
     * 
     * @return a list of avaliale {@link DiningTable} objects
     */
    @Override
    public List<DiningTable> getAllAvailableTables() {
        return dao.availableTables();
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Marks a table as no longer avaliable by delegating to the DAO layer.
     * </p>
     * 
     * @param tableNumber The unique identifier or number of the table to mark as unavaliable
     */
    @Override
    public void noLongerAvailable(String tableNumber) {
        dao.changeAvailability(tableNumber);
    }
    
}
