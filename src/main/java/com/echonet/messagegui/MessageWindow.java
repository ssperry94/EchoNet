package com.echonet.messagegui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.echonet.datahandling.DataPipe;
import com.echonet.datahandling.Table;
import com.echonet.domainmodel.Message;
import com.echonet.domainmodel.User;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;

/* TODO: add borders, colors, etc to various parts of messages 
*/

public class MessageWindow {
    private User currentUser;
    private MessageComposer composer;
    private JFrame mainWindow;
    private JPanel buttonPanel;
    private JPanel displayPanel;
    private JButton sendMessage;
    private JButton updateMessage;
    private List <JTextPane> messageDisplay;

    private User getRecipiant(final Message m) {
        DataPipe dataPipe;
        User recipiant;
        Map <String, Object> userMap;
        String firstName, lastName;
        try {
            dataPipe = new DataPipe();
            recipiant = new User(m.getRecipiantID());
            recipiant.setTable(new Table(Config.USER_TABLE));
            userMap = dataPipe.read(recipiant);

            if(userMap == null) {
                recipiant.setFirstName("Unknown");
                recipiant.setLastName("User");
                return recipiant;
            }

            recipiant.setFirstName((String) userMap.get("first_name"));
            recipiant.setLastName((String) userMap.get("last_name"));
            return recipiant;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    private JTextPane createMessageBox(final Message message) {
        User recipiant = this.getRecipiant(message);
        String messageBoxText = "From: " + recipiant.getFirstName() + " " + recipiant.getLastName() + "\nSent: " + message.getTimeStampString() + "\nContents: " + message.getContents();
        JTextPane messageBox = new JTextPane();
        messageBox.setEditable(false);
        messageBox.setText(messageBoxText);
        return messageBox;
        }
    private Message createMessage(final Map <String, Object> dataMap) throws SQLException, DataBaseNotFoundException, ClassNotFoundException {
        //all fields of a message class
        Message message; 
        int primaryID, messageID, recipiantID;
        String contents;
        Timestamp timestamp;

        primaryID = (int) dataMap.get("user_id");
        messageID = (int) dataMap.get("messageID");
        contents = (String) dataMap.get("contents");
        timestamp = java.sql.Timestamp.valueOf(dataMap.get("timestamp").toString());
        recipiantID = (int) dataMap.get("recipiants");
        message = new Message(primaryID, messageID, contents, timestamp, recipiantID);
        return message;
    }

    private void updateMessages() {
        List <Map <String, Object>> messageHistory; //holds all messages gathered from database
        JTextPane messageBox;
        try {
            Message dummymsg = new Message(this.currentUser.getID());
            DataPipe dataPipe = new DataPipe();
            messageHistory = dataPipe.multiRead(dummymsg);

            if(messageHistory.isEmpty()) {
                dummymsg.setContents("No messages.....yet!");
                messageBox = this.createMessageBox(dummymsg);
                this.messageDisplay.add(messageBox);
            }
            else {
                for (Map <String, Object> dataMap : messageHistory) {
                    dummymsg = this.createMessage(dataMap);
                    messageBox = this.createMessageBox(dummymsg);
                    messageDisplay.add(messageBox);
                    }
            }

        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
            //throw error box
        }
    }
    private void initalizeButtonsPanel() {
        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 200, 10));
        buttonPanel.setBackground(Color.GRAY);
        this.buttonPanel.add(sendMessage);
        this.buttonPanel.add(updateMessage);
    }

    private void initalizeMessagePanel() throws Exception {
        this.displayPanel = new JPanel();
        this.displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.displayPanel.setBackground(Color.BLUE);
        this.updateMessages();

        for(JTextPane messageBox : this.messageDisplay) {
            this.displayPanel.add(messageBox);
        }
    }

    private void initalize() throws Exception {
        this.messageDisplay = new ArrayList<>();
        this.mainWindow = new JFrame();
        this.mainWindow.setTitle("Messages");
        this.mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.mainWindow.setLayout(new BorderLayout(20,10));
        this.mainWindow.setSize(800, 500);
        this.mainWindow.setLocationRelativeTo(null);

        this.initalizeButtons();
        this.initalizeButtonsPanel();
        this.initalizeMessagePanel();

        this.mainWindow.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            if(composer.getMainWindow() != null) {
                composer.getMainWindow().dispose();
            }
        }
        });

        this.mainWindow.add(buttonPanel, BorderLayout.SOUTH);
        this.mainWindow.add(displayPanel, BorderLayout.CENTER);

    }

    private void initalizeButtons() {
        this.sendMessage = new JButton("Send Message");
        this.sendMessage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                composer = new MessageComposer(currentUser);
                composer.show();
            }
        });
        this.updateMessage = new JButton("Update Messages");
        this.updateMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPanel.removeAll();
                messageDisplay.clear();
                updateMessages();
                for(JTextPane messageBox : messageDisplay) {
                    displayPanel.add(messageBox);
                }
                displayPanel.updateUI();
            }});
    }

    public MessageWindow(final User currentUser) throws Exception{
        this.currentUser = currentUser;
        this.initalize();
    }

    public void show() {
        mainWindow.setVisible(true);
    }

    //for running window
    public static void main(String args[]) {
    try {
        User testUser = new User(1);
        MessageWindow window = new MessageWindow(testUser);
        window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
