package se.yrgo.services;

import java.util.List;
import se.yrgo.domain.*;

public interface TableService {
    void addTable(Table table);
    Table getTable(String tableId);
    List<Table> getAllTables();
    List<Table> getAllAvailableTables();
    
} 
