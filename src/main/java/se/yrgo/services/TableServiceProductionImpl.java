package se.yrgo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.data.BookingDao;
import se.yrgo.domain.Table;

@Service("tableService")
@Transactional
public class TableServiceProductionImpl implements TableService{
    private BookingDao dao;

    public TableServiceProductionImpl(BookingDao dao) {
        this.dao = dao;
    }

    @Override
    public void addTable(Table table) {
        dao.createTable(table);
    }

    @Override
    public Table getTable(String tableId) {
        return dao.findTableById(tableId);
    }

    @Override
    public List<Table> getAllTables() {
        return dao.allTables();
    }

    @Override
    public List<Table> getAllAvailableTables() {
        return dao.availableTables();
    }
    
}
