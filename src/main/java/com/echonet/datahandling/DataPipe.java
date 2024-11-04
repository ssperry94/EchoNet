package com.echonet.datahandling;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.echonet.domainmodel.Domain;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;
public class DataPipe {
    
    public DataPipe() {};

    public ResultSet read(final Domain d) {
        ResultSet rs;
        try {
            DataReader reader = new DataReader(Config.DATABASE_DRIVER);
            rs = reader.read(d.getTable(), d);
            return rs;
        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            //throw error message
            return null;
        }
    }   
}
