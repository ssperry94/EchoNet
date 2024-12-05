package com.echonet.domainmodel;

import java.util.Map;

import com.echonet.datahandling.Table;
/**
 * Abstract base class for all domain entities that interact with the database.
 * 
 * This class provides a structure for all domain objects that need to 
 * access and modify data in the database. Each domain object is associated 
 * with a database table and has a unique identifier.
 * 
 * Child classes must implement the {@code createMapForBackEnd()} method, 
 * which prepares a key-value mapping of the object's attributes for database operations.
 * 
 * <p>Main Responsibilities:</p>
 * <ul>
 *   <li>Store and manage the unique ID for the domain object.</li>
 *   <li>Provide access to the associated database table.</li>
 *   <li>Enforce the creation of a backend data map in child classes.</li>
 * </ul>
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
