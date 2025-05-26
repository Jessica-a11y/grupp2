package se.yrgo.services;

import java.util.List;
import se.yrgo.domain.*;

public interface TableService {
    void addTable(DiningTable table);
    DiningTable getTable(String tableId);
    List<DiningTable> getAllTables();
    List<DiningTable> getAllAvailableTables();
    
} 
