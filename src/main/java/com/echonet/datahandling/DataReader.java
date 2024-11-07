package com.echonet.datahandling;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.echonet.domainmodel.Domain;
import com.echonet.exceptions.DataBaseNotFoundException;

/**
 * Backend class that reads data from the database.
 * returns to {@code DataPipe} a {@code ResultSet}
 * 
 * @author Sam Perry - all methods
 */
class DataReader extends DataHandler {
    
    public DataReader(final String database) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        super(database);
    }

    public ResultSet read(final Table table, final Domain u) throws SQLException {
        ResultSet rs;
        String sql = this.sqlgen.queryStatement(table, u);
        Statement stmt = c.createStatement();
        rs = stmt.executeQuery(sql);
        return rs;
    }
}
