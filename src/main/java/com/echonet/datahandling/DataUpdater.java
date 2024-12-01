package com.echonet.datahandling;

import java.sql.SQLException;

import com.echonet.exceptions.DataBaseNotFoundException;

class DataUpdater extends DataHandler {
    public DataUpdater(final String database) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        super(database);
    }    

    
}
