package com.echonet.datahandling;

import java.sql.SQLException;
import java.util.List;

import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;

/**
 * A class representing a table in the database
 */
public class Table {
    private String tableName;
    private List <String> columnNames;
    private DataHanlder handler;

    /**
     * Initalizes the table object. Will querey the databse for this table, and return an SQLException if the table isn't found
     * @param tableName - name of the table to look for
     * @throws SQLException - thrown either during initalization of the DataHanlder object, or if the table cannot be found.
     * @throws ClassNotFoundException - thrown if the driver JDBC uses cannot be accessed
     * @throws DataBaseNotFoundException - thrown if the database connection cannot be established 
     */
    public Table(final String tableName) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        this.tableName = tableName;
        this.handler = new DataHanlder(Config.DATABASE_INIT);
        this.columnNames = this.handler.getTableColumnNames(tableName);
    }

    /**
     * Getter to return the column names for the table
     * @return the column names belonging to the table
     */
    public List <String> getTableColumns() {return this.columnNames;}

    /**
     * Getter to return the name of the table
     * @return the name of the table used in the instantiation of the object
     */
    public String getTableName() {return this.tableName;}

    /**
     * Queries the database to grab the current column names if there is need to update the columns
     * @throws SQLException - if the handler cannot access the table
     */
    public void updateColumnNames() throws SQLException {this.columnNames = this.handler.getTableColumnNames(this.tableName);}

    /**
     * This constructors works exactly as the original constructor, but SHOULD NOT be used outside of unit testing
     * @param tableName - name of the table
     * @param isTest - flag for indicating if this is a test or not. will throw a runtime exception if this flag is not true
     * @throws SQLException - thrown during datahanlding initilaization 
     * @throws ClassNotFoundException - thrown during datahanlding initalization
     * @throws DataBaseNotFoundException - thrown during datahanlding initalization
     */
    public Table(final String tableName, final boolean isTest) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        if(!isTest) {throw new RuntimeException("Error, flag for test is not marked 'true'. Please ensure that isTest is true, and that it is being used only in a unit test setting.");}
       
        this.tableName = tableName;
        this.handler = new DataHanlder(Config.TEST_DATABASE_INIT);
        this.columnNames = this.handler.getTableColumnNames(tableName);
    }
}