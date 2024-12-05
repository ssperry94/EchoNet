package com.echonet.domainmodel;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.echonet.datahandling.DataPipe;
import com.echonet.datahandling.Table;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;
import java.util.HashSet;

/*TODO: erase all attributes once RegistrationPanel can give them over.
*/
public class Authentication extends Domain {
    
    DataPipe read;

    //Map <String, Object> dataMap = read.read(User);

    private Map<String, Object> users;  // This should be replaced with data base map

    private void setUsers(final String username) {
        this.users = this.read.read(this, "username", username);
    }
    
    
    //needs other attributes of user 
    private User createUser() throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        User u = new User(this.getID());
        u.setUsername((String) this.users.get("username"));
        
        //replace once all attributes for user are avalible
        u.setTable(new Table(Config.USER_TABLE));
        u.setFirstName("test");
        u.setLastName("test");
        u.setBirthday("1/1/1999");
        u.setEmail("test@test.com");

        //set everything else here 
        
        return u;
    }
    public Authentication(final int userID) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        super(userID);
        read = new DataPipe();
        this.table = new Table(Config.LOGIN_TABLE);
    }

    public void setAdditionalAttributes(String firstName, String lastName, String email, String birthday) {
        this.users.put("first_name", firstName);
        this.users.put("last_name", lastName);
        this.users.put("email", email);
        this.users.put("birthday", birthday);
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
            this.users = new HashMap<>();
            this.users.put("username", username);
            this.users.put("password", password);
            if(read.write(this)) { //try writing authenticator first 
                try {
                    User newUser = this.createUser(); //if successful, try writing the user
                    return read.write(newUser);
                    
                } catch (Exception e) {
                    return false;
                }

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
    public User login(String username, String password) {
        if(username == null || password == null) {
            System.out.println("Username not found.");
            return null;
        }
        this.setUsers(username);
        
        if (this.users == null) {
            System.out.println("Username not found.");
            return null;
        }
        
        if (this.users.get("username").toString().equals(username) && this.users.get("password").toString().equals(password)) {
            try {
                User currentUser = new User((int) this.users.get("userID"));
                currentUser.setTable(new Table(Config.USER_TABLE));
                currentUser.setUsername((String) this.users.get("username"));
                currentUser.setFirstName((String) this.users.get("first_name"));
                currentUser.setLastName((String) this.users.get("last_name"));
                currentUser.setBirthday((String) this.users.get("birthday"));
                currentUser.setEmail((String) this.users.get("email"));
                
                System.out.println("Login successful.");
                return currentUser; 
            } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
                return null;
            }

        } else {
            System.out.println("Incorrect login credentials.");
            return null;
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
