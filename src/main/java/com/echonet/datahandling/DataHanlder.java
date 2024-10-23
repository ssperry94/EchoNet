package com.echonet.datahandling;

import java.io.File;
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
    private final static String driver = "org.sqlite.JDBC";     //JDBC driver - do not change
    private static String database;                            //holds the name of the database plus syntax to establish a connection
    private final String defaultDatabasePath = "echodata.db"; //path to main database
    private Connection c = null;                              //allows connection to database, creation of statements, etc
    private SqlGenerator sqlgen;                              //generates sql statements 

    public DataHanlder() throws SQLException, ClassNotFoundException {
        
        database = "jdbc:sqlite:" + defaultDatabasePath;
        Class.forName(driver);
        c = DriverManager.getConnection(database);
        sqlgen = new SqlGenerator();
    }

    //for unit testing only
    public DataHanlder(String testDataBase) throws SQLException, ClassNotFoundException {
        database = testDataBase;
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

    /*
     * checks to see if the default database exists or not
     */
    public boolean isExist() {
        File echoDataBase = new File (defaultDatabasePath);
        return echoDataBase.exists();
    }

    /*
     * Overload that can verify existance of a different database. 
     */
    public boolean isExist(String dataBasePath) {
        File echoDataBase = new File (dataBasePath);
        return echoDataBase.exists();
    }

}
