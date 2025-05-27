package se.yrgo.services;

import java.util.List;
import se.yrgo.domain.*;

/**
 * TableService provides operations for managing dining tables.
 * 
 * <p>
 * This interface allows adding new tables, retrieving tables by ID, listing all tables, listing avaliable tables
 * and marking tables as unavaliable.
 * </p>
 * 
 * @author Daniel Grahn, Jessica Olofsson, for JavaDoc: Emilia Jarleback
 */

public interface TableService {

    /**
     * Adds a new dining table to the system.
     * 
     * @param table The {@link DiningTable} object to add
     */
    void addTable(DiningTable table);

    /**
     * Retrieves a dining table by its unique ID.
     * 
     * @param tableId The unique identifier of the table
     * @return the {@link DiningTable} if found, otherwise {@code null}
     */
    DiningTable getTable(String tableId);

    /**
     * Retrieves a list of all dining tables in the system.
     * 
     * @return a {@link List} of all {@link DiningTable} objects
     */
    List<DiningTable> getAllTables();

    /**
     * Retrieves a list of all avaliable dining tables.
     * 
     * @return a {@link List} of avaliable {@link DiningTable} objects
     */
    List<DiningTable> getAllAvailableTables();

    /**
     * Marks a table as no longer avaliable for reservations.
     * 
     * @param tableNumber The unique identifier or number of the table to mark as anavaliable
     */
    public void noLongerAvailable(String tableNumber);
    
} 
