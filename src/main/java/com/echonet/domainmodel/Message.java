package com.echonet.domainmodel;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Message extends Domain {
    private String contents;
    Timestamp timeStamp;
    int messageID;

    public Message(final int userID) {
        super(userID);
    }

    public Message(final int userID, final int messageID, final String contents, final Timestamp timeStamp) {
        super(userID);
        this.messageID = messageID;
        this.contents = contents;
        this.timeStamp = timeStamp;
    }
    
    public int getMessageID() {return this.messageID;}
    public String getContents() {return this.contents;}
    public Timestamp getTimeStampObject() {return this.timeStamp;}
    public String getTimeStampString() {return this.timeStamp.toString();}

    public void setMessageID(int ID) {this.messageID = ID;}
    public void setContents(String contents) {this.contents = contents;}


    // public void setTimeStamp(Timestamp time) {this.timeStamp = timeStamp;} look into how a timestamp updates
    @Override
    public Map <Integer, Object> createMapForBackEnd() {
        Map <Integer, Object> dataMap = new HashMap<>();
        dataMap.put(0, this.getID());
        dataMap.put(1, this.messageID);
        dataMap.put(2, this.timeStamp.toString());
        dataMap.put(3, this.contents);
        return dataMap;
    }
}
