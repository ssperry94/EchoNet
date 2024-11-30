package com.echonet.datahandling;

/**
 * A class that abstracts away the SQL used to query the database
 * This is a package private class that can only be used by the classes that interact with database can instantiate 
 * Will generate statementes that will grab all data in an associated row
 * queryStatement(String table, User u) -> will return a SELECT statement getting all info from that table WHERE user_id = u.getID() 
 * insertStatement(String table) -> returns an INSERT statement that will insert values into that database 
 * 
 * 
 * @author Sam Perry - all methods
 */

//imports 
import com.echonet.domainmodel.Domain;

class SqlGenerator {

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
    public String queryStatement(final Table table, final Domain u) {
        return "SELECT * FROM " + table.getTableName() + " WHERE " + table.getTableColumns().get(0) + " = " + u.getID();
    }

    public String queryStatement(final Table table, final Domain u, final String tableColumnName, final Object value) {
        return "SELECT * FROM " + table.getTableName() + " WHERE " + tableColumnName + " = " + "\"" + value.toString() + "\"";
    }

    public String insertStatement(final Table table) {
        return "INSERT INTO " + table.getTableName() + " VALUES " + this.generateValues(table);
    }

    public String deleteStatement(final Table table, final Domain d) {
        return "DELETE FROM " + table.getTableName() + " WHERE " + table.getTableColumns().get(0) + " = " + d.getID();
    }

    public String deleteStatement(final Table table, final Domain d, final String tableColumnName, final Object value) {
        return "DELETE FROM " + table.getTableName() + " WHERE " + tableColumnName + " = " + "\"" + value.toString() + "\""; 
    }

    public String getTableInfoQuery(final String tableName) {
        return "SELECT * FROM " + tableName;
    }
}
