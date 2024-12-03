package com.echonet.domainmodel;

import com.echonet.datahandling.Table;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Post extends Domain {
    private String content;
    private Timestamp timestamp;
    private int postID;
    private int userID;
    private String imagePath; // Path to the image file 

    // Constructor to initialize with userID only
    public Post(final int userID) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        super(userID);
        this.table = new Table(Config.POSTS_TABLE);
    }

    // Constructor to initialize all fields
    public Post(final int userID, final int postID, final String content, final Timestamp timestamp, final String imagePath) 
            throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        super(userID);
        this.table = new Table(Config.POSTS_TABLE);
        this.userID = userID;
        this.postID = postID;
        this.content = content;
        this.timestamp = timestamp;
        this.imagePath = imagePath;
    }

    // Getters
    public int getPostID() { return this.postID; }
    public String getContent() { return this.content; }
    public Timestamp getTimestampObject() { return this.timestamp; }
    public String getTimestampString() { return this.timestamp.toString(); }
    public int getUserID() { return this.userID; }
    public String getImagePath() { return this.imagePath; }

    // Setters
    public void setPostID(int postID) { this.postID = postID; }
    public void setContent(String content) { this.content = content; }
    public void setTimestamp() {
        Date currentDate = new Date();
        this.timestamp = new Timestamp(currentDate.getTime());
    }
    public void setAutomaticPostID() {
        Random randomIdGenerator = new Random();
        this.postID = randomIdGenerator.nextInt(999);
    }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    @Override
    public Map<Integer, Object> createMapForBackEnd() {
        Map<Integer, Object> dataMap = new HashMap<>();
        dataMap.put(0, this.getID());
        dataMap.put(1, this.postID);
        dataMap.put(2, this.content);
        dataMap.put(3, this.timestamp.toString());
        dataMap.put(4, this.imagePath); // Include image path in the map
        return dataMap;
    }
}


//for post contructor this.table = new Table(Config.POSTS_TABLE); 