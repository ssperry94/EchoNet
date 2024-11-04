package com.echonet.datahandling;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import com.echonet.exceptions.DataBaseNotFoundException;

/**
 * Class responsible for all writing of data to the databse. Takes in a table and a Map that should both be gotten from the subclasses of Domain
 * Then writes the data in order of table columns in database
 */
public class DataWriter extends DataHandler {    
    /**
     * Populates the specified {@code PreparedStatement} with values from the map given by the Domain class
     * The key in the map corrisponds to the first column in a table, and the value is the value that belongs to that column
     * It is very important to make sure that these entries are always given in order to the map, or else a {@code SQLException} could occur
     *
     * @param pstmt the {@code PreparedStatement} to be populated with parameters
     * @param map   a {@code Map<Integer, Object>} containing the parameter index and value
     *              pairs to set in the statement
     * @throws SQLException if an SQL error occurs when setting the parameters in the statement
     */
    private void populatePreparedStatement(PreparedStatement pstmt, final Map <Integer, Object> map) throws SQLException {
        for(int i = 0; i < map.size(); i++) {
            Object data = map.get(i);

            if(data instanceof String string) {
                pstmt.setString(i + 1, string);
            }
            else {
                pstmt.setInt(i + 1, (int)data);
            }
        }
    }
    /**
     * Constructor inherited from {@code DataHandler}
     * @see {@code DataHandler}
     */
    public DataWriter (final String database) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        super(database);
    }

    /**
     * functions responisble for writing data to the database
     * 
     * @param t - takes a {@code Table} to pass to the {@code SQLGenerator}
     * @param map - takes a {@code Map} of {@code Integer} and {@code Object} to get all values to be passed into the database 
     * @throws SQLException - thown when error occurs when writing to the database
     */
    public void write(final Table t, final Map <Integer, Object> map) throws SQLException {
        String sql = sqlgen.insertStatement(t);
        PreparedStatement pstmt = c.prepareStatement(sql); 
        this.populatePreparedStatement(pstmt, map);
        pstmt.executeUpdate();
        pstmt.close(); 
    }
}
