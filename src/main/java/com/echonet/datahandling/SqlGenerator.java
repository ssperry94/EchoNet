package com.echonet.datahandling;

/*
 * A class that abstracts away the SQL used to query the database
 * This is a package private class that can only be used by the classes that interact with database can instantiate 
 * Will generate statementes that will grab all data in an associated row
 * queryStatement(String table, User u) -> will return a SELECT statement getting all info from that table WHERE user_id = u.getID() 
 * insertStatement(String table) -> returns an INSERT statement that will insert values into that database 
 */

//TODO: create a table class that will be able to store its column names so that we can easily generate sql

class SqlGenerator {

    //public methods 
    public String queryStatement(String table /*User u*/) {
        return "SELECT * FROM " + table + " WHERE user_id = " /*+ u.getID()*/;
    }

    public String insertStatement(String table) {
        return "INSERT INTO " + table + " VALUES ";
    }
}
