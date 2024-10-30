package com.echonet.datahandling;

/*
 * A class that abstracts away the SQL used to query the database
 * This is a package private class that can only be used by the classes that interact with database can instantiate 
 * Will generate statementes that will grab all data in an associated row
 * queryStatement(String table, User u) -> will return a SELECT statement getting all info from that table WHERE user_id = u.getID() 
 * insertStatement(String table) -> returns an INSERT statement that will insert values into that database 
 */

//imports 
import com.echonet.user.User;

public class SqlGenerator {

    private String generateValues(final Table table) {
        StringBuilder values = new StringBuilder("(");
        for(int i = 0; i < table.getTableColumns().size(); i++) {
            values.append("?,");
        }
        //remove the last comma
        int lastIndex = values.length() -1;
        values.deleteCharAt(lastIndex);
        values.append(")");
        return values.toString();
    }

    //public methods 
    public String queryStatement(final Table table, final User u) {
        return "SELECT * FROM " + table.getTableName() + " WHERE user_id = " + u.getID();
    }

    public String insertStatement(final Table table) {
        return "INSERT INTO " + table.getTableName() + " VALUES " + this.generateValues(table);
    }

    public String getTableInfoQuery(final String tableName) {
        return "SELECT * FROM " + tableName;
    }
}
