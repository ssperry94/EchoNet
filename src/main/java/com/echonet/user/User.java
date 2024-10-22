package com.echonet.user;

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String birthday;
    private String email;
    private int ID; 
    
    //friends list
    
    public User() {}

    // getter and setter methods for user info
    public String getFirstName(){
        return username;
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
