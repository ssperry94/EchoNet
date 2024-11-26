package com.echonet.domainmodel;

import java.util.HashMap;
import java.util.Map;


import com.echonet.datahandling.DataPipe;

public class Authentication {
    
    DataPipe read = new DataPipe();

    Map <String, Object> dataMap = read.read(User);

    private Map<String, String> users;  // This should be replaced with data base map

    public Authentication(){
        users = new HashMap<>();
    }

    // Registering a new user
    public boolean Register(String username , String password){
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password cannot be empty.");
            return false;
        }
        
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
}
