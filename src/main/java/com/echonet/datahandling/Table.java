package com.echonet.datahandling;

import java.sql.SQLException;
import java.util.List;

import com.echonet.exceptions.DataBaseNotFoundException;

public class Table {
    private String tableName;
    private List <String> columnNames;
    private DataHanlder handler;

    public Table(final String tableName) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        this.tableName = tableName;
        this.handler = new DataHanlder();
        this.columnNames = this.handler.getTableColumnNames(tableName);
    }

    public List <String> getTableColumns() {return this.columnNames;}
    public String getTableName() {return this.tableName;}
    public void updateColumnNames() throws SQLException {this.columnNames = this.handler.getTableColumnNames(this.tableName);}
}