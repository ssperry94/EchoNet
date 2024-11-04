package com.echonet.datahandling;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.echonet.domainmodel.Domain;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;
public class DataPipe {
    
    public DataPipe() {};

    /**
     * Method used by client code to read information from the database. must pass a subclass of Domain
     * @param d - a subclass of Domain that must have a table and ID field
     * @return - ResultSet containg all of the data in the table associated with the class passed in, null if any exceptions were thrown
     */
    public ResultSet read(final Domain d) {
        ResultSet rs;
        try (DataReader reader = new DataReader(Config.DATABASE_INIT)) {
            rs = reader.read(d.getTable(), d);
            return rs;
        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            //throw error message
            return null;
        }
    }   

    /**
     * Method used by clinet code to write to database. Must pass in a subclass of Domain
     * @param d - a subclass of Domian. must have a table, and an overriden createMapForBackEnd() function
     * @return - true if data was successfully written, false if any exceptions were thrown
     */
    public boolean write(final Domain d) {
        Map <Integer, Object> dataMap;

        try (DataWriter writer = new DataWriter(Config.DATABASE_INIT)){
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
        try (DataReader reader = new DataReader(Config.TEST_DATABASE_INIT)) {
            rs = reader.read(d.getTable(), d);
            return rs;
        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            //throw error message
            return null;
        }
    }   
    
    //for unit test 
    public boolean write(final Domain d, boolean isTest) {
        Map <Integer, Object> dataMap;

        try (DataWriter writer = new DataWriter(Config.TEST_DATABASE_INIT)) {
            dataMap = d.createMapForBackEnd();
            writer.write(d.getTable(), dataMap);
            return true;
        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            //throw error message
            return false;
        }
    }
}
