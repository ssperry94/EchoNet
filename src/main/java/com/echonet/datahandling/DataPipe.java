package com.echonet.datahandling;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.echonet.domainmodel.Domain;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;
public class DataPipe {
    
    public DataPipe() {};

    public ResultSet read(final Domain d) {
        ResultSet rs;
        try {
            DataReader reader = new DataReader(Config.DATABASE_INIT);
            rs = reader.read(d.getTable(), d);
            return rs;
        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            //throw error message
            return null;
        }
    }   

    public boolean write(final Domain d) {
        Map <Integer, Object> dataMap;

        try {
            DataWriter writer = new DataWriter(Config.DATABASE_INIT);
            dataMap = d.createMapForBackEnd();
            writer.write(d.getTable(), dataMap);
            return true;
        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            //throw error message
            return false;
        }

    }
    //for unit tests only
    public ResultSet read(final Domain d, boolean isTest) {
        ResultSet rs;
        try {
            DataReader reader = new DataReader(Config.TEST_DATABASE_INIT);
            rs = reader.read(d.getTable(), d);
            return rs;
        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            //throw error message
            return null;
        }
    }   
}
