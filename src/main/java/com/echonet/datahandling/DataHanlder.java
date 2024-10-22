package com.echonet.datahandling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * Parent class that establishes connection to database. 
 */
public class DataHanlder {
    private final static String driver = "org.sqlite.JDBC";
    private final static String database = "jdbc:sqlite:echodata.db";
    private Connection c = null;
    private SqlGenerator sqlgen;

    public DataHanlder() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        c = DriverManager.getConnection(database);
        sqlgen = new SqlGenerator();
    }

    public ArrayList<String> getTableColumnNames(String tableName) throws SQLException {
        ArrayList <String> columnNames = new ArrayList<>();
        String sql = sqlgen.getTableInfoQuery(tableName);

        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        ResultSetMetaData rsmd = rs.getMetaData();
        for(int i = 0; i < rsmd.getColumnCount(); i++) {
            columnNames.add(rsmd.getColumnName(i));
        }
        return columnNames;
    }

}
