package com.echonet.domainmodel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.echonet.datahandling.DataPipe;
import com.echonet.datahandling.Table;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;

/*TODO: uncomment getFriends in createMapForBackend()
 * make createFriendsList private
 * ask if friends should be a list of Users or a list of Friends
*/
public class User extends Domain {

    protected String firstName;
    protected String lastName;
    protected String username;
    protected String birthday;
    protected String email; 
    protected String tempfriends;
    private List<Friend> friends;
    
    public void createFriendsList(final String friendsStr) throws SQLException, DataBaseNotFoundException, ClassNotFoundException {
        //locals
        this.friends = new ArrayList<>(); 
        String [] friendIDs = friendsStr.split(",");
        DataPipe dataPipe = new DataPipe();
        Map <String, Object> dataMap;

        //main for loop
        for (String friend : friendIDs) {
            //convert int to string and populate datamap
            int convertedInt = Integer.parseInt(friend);
            Friend nextFriend = new Friend(convertedInt);
            nextFriend.setTable(new Table(Config.USER_TABLE));
            dataMap = dataPipe.read(nextFriend);

            //fill all fields of user
            nextFriend.setFirstName((String) dataMap.get("first_name"));
            nextFriend.setLastName((String) dataMap.get("last_name"));
            nextFriend.setUsername((String) dataMap.get("username"));
            nextFriend.setBirthday((String) dataMap.get("birthday"));
            nextFriend.setEmail((String) dataMap.get("email"));

            //upcast to friend and add to list
            this.friends.add(nextFriend);
        }
    }
    public User(final int ID) {super(ID);} //added this constructor for unit testing - may delete later

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
     * @throws DataBaseNotFoundException 
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public User(final int ID, final List<String> attributeArray) throws ClassNotFoundException, SQLException, DataBaseNotFoundException {
        super(ID);
        this.table = new Table(Config.USER_TABLE);
        this.friends = new ArrayList<>();
        for(int i = 0; i < attributeArray.size(); i++) {
            switch(i) {
                case 0: this.firstName = attributeArray.get(i); break;
                case 1: this.lastName = attributeArray.get(i); break;
                case 2: this.username = attributeArray.get(i); break;
                case 3: this.birthday = attributeArray.get(i); break;
                case 4: this.email = attributeArray.get(i); break;
                case 5: this.tempfriends = attributeArray.get(i); List<String> friends = new ArrayList<>(Arrays.asList(tempfriends.split(","))); break;
                default: System.err.println("No more attributes to set."); break;
            }
        }
    }

    // Method to add a friend by creating a Friendship
    public void addFriend(User friend) {
        if (friend != this /*&& !isFriendsWith(friend)*/) {
            Friend newFriend = new Friend(this, friend);
            friends.add(newFriend);
            friend.friends.add(newFriend);  // Mutual friendship
        }
    }

    // Check if a user is already a friend
    //public boolean isFriendsWith(User friend) {
    //    return friends.stream().anyMatch(f -> f.involves(friend));
    //}

    // Method to retrieve all friends as a list of Users
    public List<User> getFriends() {
        List<User> friendsList = new ArrayList<>();
        for (Friend friends : friends) {
            if (friends.getUser1().equals(this)) {
                friendsList.add(friends.getUser2());
            } else {
                friendsList.add(friends.getUser1());
            }
        }
        return friendsList;
    }

    public List <Friend> getterFriends() {
        return this.friends;
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
        //dataMap.put(6, this.getFriends());
        return dataMap;
    }
}
