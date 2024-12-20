package com.echonet.domainmodel;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.echonet.datahandling.Table;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;

public class Message extends Domain {
    private String contents;
    private Timestamp timeStamp;
    private int messageID;
    private int recipiantID;

    public Message(final int userID) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        super(userID);
        this.table = new Table(Config.MESSAGES_TABLE);
    }

    public Message(final int userID, final int messageID, final String contents, final Timestamp timeStamp, final int recipiantID) throws SQLException, 
                   ClassNotFoundException, DataBaseNotFoundException {
        super(userID);
        this.table = new Table(Config.MESSAGES_TABLE);
        this.messageID = messageID;
        this.contents = contents;
        this.timeStamp = timeStamp;
        this.recipiantID = recipiantID;
    }

    public int getMessageID() {return this.messageID;}
    public String getContents() {return this.contents;}
    public Timestamp getTimeStampObject() {return this.timeStamp;}
    public String getTimeStampString() {return this.timeStamp.toString();}
    public int getRecipiantID() {return this.recipiantID;}

    public void setMessageID(int ID) {this.messageID = ID;}
    
    public void setAutomaticMessageID() {
        Random randomIdGenerator = new Random();
        this.messageID = randomIdGenerator.nextInt(999);
    }
    public void setContents(String contents) {this.contents = contents;}

    public void setRecipiantID(final int ID) {this.recipiantID = ID;}
    public void setTimeStamp() {
        Date currentDate = new Date();
        this.timeStamp = new Timestamp(currentDate.getTime());
    }

    // public void setTimeStamp(Timestamp time) {this.timeStamp = timeStamp;} look into how a timestamp updates
    @Override
    public Map <Integer, Object> createMapForBackEnd() {
        Map <Integer, Object> dataMap = new HashMap<>();
        dataMap.put(0, this.getID());
        dataMap.put(1, this.messageID);
        dataMap.put(2, this.contents);
        dataMap.put(3, this.timeStamp.toString());
        dataMap.put(4, this.recipiantID);
        return dataMap;
    }
}
