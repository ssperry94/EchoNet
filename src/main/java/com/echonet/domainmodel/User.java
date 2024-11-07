package com.echonet.domainmodel;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
public class User extends Domain {

    protected String firstName;
    protected String lastName;
    protected String username;
    protected String birthday;
    protected String email; 
    
    public User(final int ID) {super(ID);} //added this constructor for unit testing - may delete later
    public User(final int ID, final ResultSet rs) {
        super(ID);

        //TODO: add all user fields and use result set to populate user information. also add code to instantiate user table
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
