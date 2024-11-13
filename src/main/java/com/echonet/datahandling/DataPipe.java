package com.echonet.datahandling;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.echonet.domainmodel.Domain;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;

/**
 * Class that acts as a pipeline between the frontend and backend
 * for each method, pass a subclass of Domain, and it will read/write/remove all data associated with that object.
 * 
 * @author Sam Perry - all methods
 */
public class DataPipe {
    /**
     * private method that creates a Map from a result set. keys are the table names, and the values are the data corrisponding with each row
     * @param rs - {@code ResultSet} generated from the read method
     * @param t - {@code Table} passed in from (@code Domain)
     * @return - {@code Map} with table column names as keys, data in those columns as values
     * @throws SQLException - if any database related errors occur
     */
    private Map <String, Object> createMap(final ResultSet rs, final Table t) throws SQLException {
        Map <String, Object> data = new HashMap<>();
        List <String> columnNames = t.getTableColumns();
        for(int i = 1; i <= columnNames.size(); i++) {
                Object value = rs.getObject(i);
                if(value == null) {
                    return null; //returns null if any value wasn't gathered from the database
                }
                else {
                    data.put(columnNames.get(i -1), value);
                }
            }

        if(data.isEmpty()) 
            return null;
        else 
            return data;
    }

    private List <Map <String, Object>> createMultipleMaps(final ResultSet rs, final Table t) throws SQLException {
        List <Map <String, Object>> dataList = new ArrayList<>();
        while(rs.next()) {
            Map <String, Object> dataMap = this.createMap(rs, t);
            dataList.add(dataMap);
        }
        return dataList;
    }

    public DataPipe() {};

    /**
     * Method used by client code to read information from the database. must pass a subclass of Domain
     * @param d - a subclass of {@code Domain} that must have a table and ID field
     * @return - {@code ResultSet} containg all of the data in the table associated with the class passed in, null if any exceptions were thrown
     */
    public Map <String, Object> read(final Domain d) {
        ResultSet rs;
        Map <String, Object> data;
        try (DataReader reader = new DataReader(Config.DATABASE_INIT)) {
            rs = reader.read(d.getTable(), d);
            data = this.createMap(rs, d.getTable());
            return data;
        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            //throw error message
            return null;
        }
    }   

    /**
     * Overload of read that allows data to be retrieved from the database based on criteria other than the primary key
     * @param d - subclass of domain
     * @param tableColumnName - the column in which you wish to search for
     * @param value - the value of the column
     * @return
     */
    public Map <String, Object> read(final Domain d, String tableColumnName, Object value) {
        ResultSet rs;
        Map <String, Object> data;
        try (DataReader reader = new DataReader(Config.DATABASE_INIT)) {
            rs = reader.read(d.getTable(), d, tableColumnName, value);
            data = this.createMap(rs, d.getTable());
            return data;
        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            return null;
        }
    }

    public List <Map <String, Object>> multiRead(final Domain d) {
        ResultSet rs;
        List <Map <String, Object>> dataList;
        try (DataReader reader = new DataReader(Config.DATABASE_INIT)) {
            rs = reader.read(d.getTable(), d);
            dataList = this.createMultipleMaps(rs, d.getTable());
            return dataList;
        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            return null;
        }
    }

    public List <Map <String, Object>> multiRead(final Domain d, final String tableColumnName, final Object value) {
        ResultSet rs;
        List <Map <String, Object>> dataList;
        try (DataReader reader = new DataReader(Config.DATABASE_INIT)) {
            rs = reader.read(d.getTable(), d, tableColumnName, value);
            dataList = this.createMultipleMaps(rs, d.getTable());
            return dataList;
        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            e.printStackTrace();
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

    /**
     * Removes data based on primary key. This method removes an entire row, so be sure when calling it
     * @param d - a subclass of domain. Must have a table field
     * @return - true if data was successfully deleted, false if an exception was thrown
     */
    public boolean remove(final Domain d) {
        try(DataRemover remover = new DataRemover(Config.DATABASE_INIT)) {
            remover.remove(d.getTable(), d);
            return true;
        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            return false;
        }
    }
    //for unit tests only
    public Map <String, Object> read(final Domain d, boolean isTest) {
        ResultSet rs;
        Map <String, Object> data;
        try (DataReader reader = new DataReader(Config.TEST_DATABASE_INIT)) {
            rs = reader.read(d.getTable(), d);
            data = this.createMap(rs, d.getTable());
            return data;
        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }   
    
    //for unit test 
    public boolean write(final Domain d, boolean isTest) {
        Map <Integer, Object> dataMap;
        System.out.println("Calling write");
        try (DataWriter writer = new DataWriter(Config.TEST_DATABASE_INIT)) {
            dataMap = d.createMapForBackEnd();
            writer.write(d.getTable(), dataMap);
            return true;
        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean remove(final Domain d, boolean isTest) {
        try(DataRemover remover = new DataRemover(Config.TEST_DATABASE_INIT)) {
            remover.remove(d.getTable(), d);
            return true;
        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            return false;
        }
    }
}
