package com.echonet.user;

import java.sql.ResultSet;

import com.echonet.datahandling.Table;
public class User {

    protected Table userTable;
    private String firstName;
    private String lastName;
    private String username;
    private String birthday;
    private String email;
    private int ID; 
    
    public User(final int ID, final ResultSet rs) {
        this.ID = ID;

        //TODO: add all user fields and use result set to populate user information. also add code to instantiate user table
    }

    // getter and setter methods for user info
    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
   
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
    
 
    public int getID(){
        return ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }
    
}
