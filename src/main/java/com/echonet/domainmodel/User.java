package com.echonet.domainmodel;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.echonet.datahandling.Table;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;
public class User extends Domain {

    protected String firstName;
    protected String lastName;
    protected String username;
    protected String birthday;
    protected String email; 
    
    public User(final int ID) /*throws SQLException, ClassNotFoundException, DataBaseNotFoundException*/ { //will need to uncomment when table field is ready
        super(ID);
        //this.table = new Table(Config.USER_TABLE);
    } //added this constructor for unit testing - may delete later

    /**
     * Instantiates the User class using an ID, and an array containg the rest of the attribtues
     * Indexes:
     * 0 - first name
     * 1 - last name
     * 2 - username
     * 3 - birthday
     * 4 - email
     * @param ID an integer representing the primary key
     * @param attributeArray - array containing all the attributes
     */
    public User(final int ID, final List<String> attributeArray) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        super(ID);
        this.table = new Table(Config.USER_TABLE);
        for(int i = 0; i < attributeArray.size(); i++) {
            switch(i) {
                case 0: this.firstName = attributeArray.get(i); break;
                case 1: this.lastName = attributeArray.get(i); break;
                case 2: this.username = attributeArray.get(i); break;
                case 3: this.birthday = attributeArray.get(i); break;
                case 4: this.email = attributeArray.get(i); break;
                default: System.err.println("No more attributes to set."); break;
            }
        }
    }

    // getter and setter methods for user info
    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
   
    public String getLastName() {return this.lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }

    public String getBirthday(){
        return birthday;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public Map <Integer, Object> createMapForBackEnd() {
        Map <Integer, Object> dataMap = new HashMap<>();
        dataMap.put(0, this.getID());
        dataMap.put(1, this.firstName);
        dataMap.put(2, this.lastName);
        dataMap.put(3, this.username);
        dataMap.put(4, this.birthday);
        dataMap.put(5, this.email);
        return dataMap;
    }
}
