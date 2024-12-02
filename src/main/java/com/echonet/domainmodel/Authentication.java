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

    private Map<String, String> users;  // This should be replaced with data base map

    private void setUsers(final String username) {
        Map <String, Object> dataMap = read.read(this, "username", username);
        this.users.put("username", (String) dataMap.get("username"));
    }

    public Authentication(final int userID) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        super(userID);
        read = new DataPipe();
        this.table = new Table(Config.LOGIN_TABLE);
        users = new HashMap<>();
    }

    // Registering a new user
    public boolean Register(String username , String password){
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password cannot be empty.");
            return false;
        }
        //get items from database into users
        
        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
            return false;
        }
        
        users.put(username, password);
        System.out.println("User registered successfully.");
        return true;
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
