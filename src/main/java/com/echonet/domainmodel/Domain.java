package com.echonet.domainmodel;

import java.util.Map;

import com.echonet.datahandling.Table;
/**
 * This class is used as a parent for all classes that will acess the database
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
