package com.echonet.user;

public class User {

    private String firstName;
    private String lastName;
    private String username;
    
    public User() {}

    // getter and setter methods for user info
    public void getFirstName(String firstName){
        this.firstName = firstName;
    }

    public String setFirstName(String firstName){
        this.firstName = firstName;
        return firstName;
    }
   
    public setUsername(String username){
        this.username = username;
    }
    
    public String getUsername(){
        return username;
    }
   
    
 
    
    
}
