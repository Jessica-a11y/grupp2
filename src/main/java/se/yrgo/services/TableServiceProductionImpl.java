package se.yrgo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.data.BookingDao;
import se.yrgo.domain.DiningTable;

@Service("tableService")
@Transactional
public class TableServiceProductionImpl implements TableService{
    private BookingDao dao;

    public TableServiceProductionImpl(BookingDao dao) {
        this.dao = dao;
    }

    @Override
    public void addTable(DiningTable table) {
        dao.createTable(table);
    }

    @Override
    public DiningTable getTable(String tableId) {
        return dao.findTableById(tableId);
    }

    @Override
    public List<DiningTable> getAllTables() {
        return dao.allTables();
    }

    @Override
    public List<DiningTable> getAllAvailableTables() {
        return dao.availableTables();
    }

    @Override
    public void noLongerAvailable(String tableNumber) {
        dao.changeAvailability(tableNumber);
    }
    
}
