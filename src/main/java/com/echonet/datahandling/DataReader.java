package com.echonet.datahandling;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.echonet.exceptions.DataBaseNotFoundException;

public class DataReader extends DataHandler {
    
    public DataReader(final String database) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        super(database);
    }

    public ResultSet read(Table table) throws SQLException {
        ResultSet rs;
        //pass table to sql gen to generate a prepared statement
        //exectute statement
        return rs;
    }
}
