package com.echonet.domainmodel;

import java.util.Map;

import com.echonet.datahandling.Table;
/**
 * Abstract base class for all domain entities that interact with the database.
 * 
 * Contains all fields that {@link DataPipe} will use to gather information on what is 
 * being passed in, and what table to look for 
 * 
 * Child classes must implement the {@code createMapForBackEnd()} method, 
 * which prepares a {@link java.util.Map} containing all relevant information for writing to the database
 * 
 * @author Sam Perry
 */
abstract public class Domain {
    //protected fields
    protected Table table;
    private int ID;
    public Domain(int ID) {this.ID = ID;} //constructor

    public int getID() {return this.ID;}
    public void setID(int ID) {this.ID = ID;}

    public Table getTable() {return this.table;}
    public void setTable(Table table) {this.table = table;}

    abstract public Map <Integer, Object> createMapForBackEnd();
}
