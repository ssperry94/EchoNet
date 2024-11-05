package com.echonet.datahandling;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.echonet.domainmodel.Domain;
import com.echonet.exceptions.DataBaseNotFoundException;

public class DataReader extends DataHandler {
    
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
