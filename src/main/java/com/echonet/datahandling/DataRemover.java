package com.echonet.datahandling;

import java.sql.SQLException;

import com.echonet.domainmodel.Domain;
import com.echonet.exceptions.DataBaseNotFoundException;

public class DataRemover extends DataHandler {
    
    public DataRemover(final String database) throws SQLException, ClassNotFoundException, DataBaseNotFoundException{
        super(database);
    }

    public void remove(final Table t, final Domain d) {
        
    }
}
