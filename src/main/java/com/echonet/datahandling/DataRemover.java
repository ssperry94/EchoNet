package com.echonet.datahandling;

import java.sql.SQLException;
import java.sql.Statement;

import com.echonet.domainmodel.Domain;
import com.echonet.exceptions.DataBaseNotFoundException;

/**
 * Backend class that removes data from the database
 * 
 * @author Sam Perry - all methods
 */
class DataRemover extends DataHandler {
    
    public DataRemover(final String database) throws SQLException, ClassNotFoundException, DataBaseNotFoundException{
        super(database);
    }

    public void remove(final Table t, final Domain d) throws SQLException {
        String sql = sqlgen.deleteStatement(t, d);
        Statement stmt = c.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    public void remove(final Table t, final Domain d, final String tableColumnName, final Object value) throws SQLException {
        String sql = sqlgen.deleteStatement(t, d, tableColumnName, value);
        Statement stmt = c.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }
}
