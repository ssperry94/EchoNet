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
import com.echonet.utilities.Config;
/**
 * Parent class that establishes connection to database. 
 */
public class DataHandler implements AutoCloseable {
    private final static String driver = Config.DATABASE_DRIVER;     //JDBC driver - do not change
    private static String database;                            //holds the name of the database plus syntax to establish a connection
    protected Connection c = null;                              //allows connection to database, creation of statements, etc
    protected SqlGenerator sqlgen;                              //generates sql statements 

    /**
     * Constructor that establishes connection to the database. Does not take any arguements, since there is only one offical database for testing
     * @throws SQLException - occurs when the database is found, but a connection cannot be made
     * @throws ClassNotFoundException - occurs when the JDBC driver cannot be found 
     * @throws DataBaseNotFoundException - occurs when the database file cannot be found
    */
    public DataHandler(final String database) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        this.database = database;
        
        if(!this.isExist(this.database)) {throw new DataBaseNotFoundException();}

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
        for(int i = 1; i <= rsmd.getColumnCount(); i++) {
            columnNames.add(rsmd.getColumnName(i));
        }

        stmt.close();
        rs.close();
        return columnNames;
    }

   /**
    * checks to see if the main database can be located
    * takes the database field and finds the last of the delimiter (":"). then it grabs everything after as the path, and returns if that path can
    * be found or not
    * @return - true if the database can be found, false if it cannot
    */
    public boolean isExist(final String database) {
        File databaseCheck;
        String databasePath;
        char databaseToken = ':';
        int beginningOfPath = database.lastIndexOf(databaseToken);

        if(beginningOfPath < 0) {
            return false;
        }
        
        databasePath = database.substring(beginningOfPath + 1);

        databaseCheck = new File(databasePath);
            
        return databaseCheck.exists();
    }

    @Override
    public void close() throws SQLException {
        c.close();
    }
}
