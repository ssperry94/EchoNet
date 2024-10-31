package com.echonet.domainmodel;

import com.echonet.datahandling.Table;
/**
 * This class is used as a parent for all classes that will acess the database
 */
public class Domain {
    //protected fields
    protected Table table;

    public Domain() {} //constructor

    public Table getTable() {return this.table;}
    public void setTable(Table table) {this.table = table;}


}
