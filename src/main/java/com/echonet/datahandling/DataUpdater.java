package com.echonet.datahandling;

import java.sql.SQLException;
import java.sql.Statement;

import com.echonet.domainmodel.Domain;
import com.echonet.exceptions.DataBaseNotFoundException;

class DataUpdater extends DataHandler {
    public DataUpdater(final String database) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        super(database);
    }    

    public void update(final Domain d, String tableColumnName, Object newValue) throws SQLException {
        String sql = this.sqlgen.updateStatement(d.getTable(), d, tableColumnName, newValue); 
        Statement stmt = this.c.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }
}
