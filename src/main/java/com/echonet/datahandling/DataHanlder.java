package com.echonet.datahandling;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.echonet.exceptions.DataBaseNotFoundException;
/**
 * Parent class that establishes connection to database. 
 */
public class DataHanlder {
    private final static String driver = "org.sqlite.JDBC";     //JDBC driver - do not change
    private static String database;                            //holds the name of the database plus syntax to establish a connection
    private final String defaultDatabasePath = "echodata.db"; //path to main database
    private Connection c = null;                              //allows connection to database, creation of statements, etc
    private SqlGenerator sqlgen;                              //generates sql statements 

    /**
     * Constructor that establishes connection to the database. Does not take any arguements, since there is only one offical database for testing
     * @throws SQLException - occurs when the database is found, but a connection cannot be made
     * @throws ClassNotFoundException - occurs when the JDBC driver cannot be found 
     * @throws DataBaseNotFoundException - occurs when the database file cannot be found
    */
    public DataHanlder() throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        if(!this.isExist()) {throw new DataBaseNotFoundException();}

        database = "jdbc:sqlite:" + defaultDatabasePath;
        Class.forName(driver);
        c = DriverManager.getConnection(database);
        sqlgen = new SqlGenerator();
    }

    /**
     * Parses the database and grabs the column names of the desired table. Populates the columnNames field of the Table class
     * @param tableName - name of the targeted table
     * @return - an arraylist containing the column names of the table
     * @throws SQLException - thrown when executing the query runs into an error
     */
    public ArrayList<String> getTableColumnNames(final String tableName) throws SQLException {
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

   /**
    * checks to see if the main database can be located
    * @return - true if the defaultDataBasePath can be found, false if it cannot
    */
    public boolean isExist() {
        File echoDataBase = new File (defaultDatabasePath);
        return echoDataBase.exists();
    }

    /* Below these functions are only used for unit testing to test different kinds of errors. DO NOT CALL OUTSIDE OF UNIT TESTS */

    /**
     * constructor that can takes a formated database and a path to a database. for Unit testing ONLY!!!
     * @param testDataBase - formatted string database of the format jdbc:sqlite:mydb.db
     * @param testDataBasePath - path to the desired database
     * @throws SQLException - occurs when the database is found, but a connection cannot be made
     * @throws ClassNotFoundException - occurs when the JDBC driver cannot be found 
     * @throws DataBaseNotFoundException - occurs when the database file cannot be found
     */
    public DataHanlder(final String testDataBase, final String testDataBasePath) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        if(!this.isExist(testDataBasePath)) {throw new DataBaseNotFoundException();}
        database = testDataBase;
        Class.forName(driver);
        c = DriverManager.getConnection(database);
        sqlgen = new SqlGenerator();
    }

    /**
     * checks to see if any db in the project exists. Used in unit testing ONLY!!
     * @param dataBasePath - path to the database
     * @return - true if the database can be found on the given path, false if it does not
     */
    public boolean isExist(final String dataBasePath) {
        File echoDataBase = new File (dataBasePath);
        return echoDataBase.exists();
    }
}
