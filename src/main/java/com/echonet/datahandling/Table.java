package com.echonet.datahandling;

import java.sql.SQLException;
import java.util.List;

import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;

public class Table {
    private String tableName;
    private List <String> columnNames;
    private DataHanlder handler;

    public Table(final String tableName) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        this.tableName = tableName;
        this.handler = new DataHanlder(Config.DATABASE_INIT);
        this.columnNames = this.handler.getTableColumnNames(tableName);
    }

    public List <String> getTableColumns() {return this.columnNames;}
    public String getTableName() {return this.tableName;}
    public void updateColumnNames() throws SQLException {this.columnNames = this.handler.getTableColumnNames(this.tableName);}

    /**
     * 
     * @param tableName
     * @param isTest
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws DataBaseNotFoundException
     */
    public Table(final String tableName, final boolean isTest) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        if(!isTest) {throw new RuntimeException("Error, flag for test is not marked 'true'. Please ensure that isTest is true, and that it is being used only in a unit test setting.");}
       
        this.tableName = tableName;
        this.handler = new DataHanlder(Config.TEST_DATABASE_INIT);
        this.columnNames = this.handler.getTableColumnNames(tableName);
    }
}