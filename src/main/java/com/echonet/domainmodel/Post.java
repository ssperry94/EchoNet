package com.echonet.domainmodel;

import javax.swing.*;

import com.echonet.utilities.Config;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.datahandling.Table;

import java.awt.*;
import java.security.Timestamp;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


public class Post extends Domain {
    private int userID;
    private int postID;
    
    public String content;
    public String timestamp;

    public int getPostID(){

        return postID;
    }

    public Post(final int userID, final int postID, final String content)throws SQLException, ClassNotFoundException, DataBaseNotFoundException{
        super(userID);
        this.table = new Table(Config.POSTS_TABLE);
        this.content = content;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    public JLabel getPostLabel(){
        String formattedText = "<html><div style='width:250px;'><b>[" + timestamp + "]</b><br>" + content + "</div></html>";

        JLabel postLabel = new JLabel(formattedText);
        postLabel.setPreferredSize(new Dimension(300, 60));
        postLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return postLabel;
    
    }

    @Override
    public Map <Integer, Object> createMapForBackEnd() {
        Map <Integer, Object> dataMap = new HashMap<>();
        dataMap.put(0, this.getID());
        dataMap.put(1, this.postID);
        dataMap.put(2, this.content);
        dataMap.put(3, this.timestamp.toString());
        return dataMap;
    }
    
    

}


//for post contructor this.table = new Table(Config.POSTS_TABLE); 