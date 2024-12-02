package com.echonet.domainmodel;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.echonet.datahandling.DataPipe;
import com.echonet.datahandling.Table;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;

public class Authentication extends Domain {
    
    DataPipe read;

    //Map <String, Object> dataMap = read.read(User);

    private Map<String, Object> users;  // This should be replaced with data base map

    private void setUsers(final String username) {
        this.users = this.read.read(this, "username", username);
    }
    
    
    //needs other attributes of user 
    private User createUser() {
        User u = new User(this.getID());
        u.setUsername((String) this.users.get("username"));
        //set everything else here 
        
        return u;
    }
    public Authentication(final int userID) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        super(userID);
        read = new DataPipe();
        this.table = new Table(Config.LOGIN_TABLE);
    }

    // Registering a new user
    public boolean Register(String username , String password){
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password cannot be empty.");
            return false;
        }
        //get items from database into users
        this.setUsers(username);
        
        //if users is null, no other use has been created with this login creds
        if(this.users == null) {
            if(read.write(this)) {
                User newUser = this.createUser();
                return read.write(newUser);
            }
            else {
                return false;
            }
        }
        else if (this.users.get("username").toString().equals(username)) {
            System.out.println("Username already exists.");
            return false;
        }
        else {
            return false; //failed for some unknown reason
        }
    }

    // log in an existing user
    public boolean login(String username, String password) {
        if (!users.containsKey(username)) {
            System.out.println("Username not found.");
            return false;
        }
        
        if (password.equals(users.get(username))) {
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Incorrect password.");
            return false;
        }
    }

    @Override
    public Map <Integer, Object> createMapForBackEnd() {
        Map <Integer, Object> dataMap = new HashMap<>();
        dataMap.put(0, this.getID());
        dataMap.put(1, this.users.get("username"));
        dataMap.put(2, this.users.get("password"));
        return dataMap;
    }
}
