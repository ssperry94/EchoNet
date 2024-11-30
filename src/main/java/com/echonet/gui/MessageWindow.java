package com.echonet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.echonet.datahandling.DataPipe;
import com.echonet.datahandling.Table;
import com.echonet.domainmodel.Message;
import com.echonet.domainmodel.User;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;

public class MessageWindow extends JPanel {

    private User currentUser;
    private MainFrame mainFrame;
    private JPanel buttonPanel;
    private JPanel displayPanel;
    private JButton sendMessage;
    private JButton updateMessage;
    private JButton backButton; // Back button
    private List<JTextPane> messageDisplay;

    public MessageWindow(final User currentUser, MainFrame mainFrame) throws Exception {
        this.currentUser = currentUser;
        this.mainFrame = mainFrame; // Store MainFrame for back navigation
        this.messageDisplay = new ArrayList<>();

        this.setLayout(new BorderLayout(20, 10));

        initializeButtons();
        initializeButtonsPanel();
        initializeMessagePanel();

        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(displayPanel, BorderLayout.CENTER);
    }

    private User getRecipiant(final Message m) {
        DataPipe dataPipe;
        User recipiant;
        Map<String, Object> userMap;
        try {
            dataPipe = new DataPipe();
            recipiant = new User(m.getRecipiantID());
            recipiant.setTable(new Table(Config.USER_TABLE));
            userMap = dataPipe.read(recipiant);

            if (userMap == null) {
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
        String messageBoxText = "From: " + recipiant.getFirstName() + " " + recipiant.getLastName()
                + "\nSent: " + message.getTimeStampString() + "\nContents: " + message.getContents();
        JTextPane messageBox = new JTextPane();
        messageBox.setEditable(false);
        messageBox.setText(messageBoxText);

        // Set the background color
        messageBox.setBackground(new Color(146, 199, 195)); // Light blue color
        messageBox.setForeground(Color.BLACK); // Optional: Set text color
        messageBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Optional: Add a border

        return messageBox;
    }

    private Message createMessage(final Map<String, Object> dataMap)
            throws SQLException, DataBaseNotFoundException, ClassNotFoundException {
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
        List<Map<String, Object>> messageHistory;
        JTextPane messageBox;
        try {
            Message dummymsg = new Message(this.currentUser.getID());
            DataPipe dataPipe = new DataPipe();
            messageHistory = dataPipe.multiRead(dummymsg);

            if (messageHistory.isEmpty()) {
                dummymsg.setContents("No messages.....yet!");
                messageBox = this.createMessageBox(dummymsg);
                this.messageDisplay.add(messageBox);
            } else {
                for (Map<String, Object> dataMap : messageHistory) {
                    dummymsg = this.createMessage(dataMap);
                    messageBox = this.createMessageBox(dummymsg);
                    messageDisplay.add(messageBox);
                }
            }

        } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void initializeButtons() {
        this.sendMessage = new JButton("Send Message");
        this.sendMessage.addActionListener(e -> {
            // Open the MessageComposer when clicked
            MessageComposer composer = new MessageComposer(currentUser);
            composer.show();
        });

        this.updateMessage = new JButton("Update Messages");
        this.updateMessage.addActionListener(e -> {
            displayPanel.removeAll();
            messageDisplay.clear();
            updateMessages();
            for (JTextPane messageBox : messageDisplay) {
                displayPanel.add(messageBox);
            }
            displayPanel.updateUI();
        });

        this.backButton = new JButton("Back to Home");
        this.backButton.addActionListener(e -> {
            // Navigate back to HomePanel
            mainFrame.showPanel("HomePanel");
        });
    }

    private void initializeButtonsPanel() {
        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        this.buttonPanel.setBackground(Color.GRAY);
        this.buttonPanel.add(backButton); // Add back button
        this.buttonPanel.add(sendMessage);
        this.buttonPanel.add(updateMessage);
    }

    private void initializeMessagePanel() {
        this.displayPanel = new JPanel();
        this.displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.displayPanel.setBackground(new Color(61, 63, 71));
        updateMessages();

        for (JTextPane messageBox : this.messageDisplay) {
            this.displayPanel.add(messageBox);
        }
    }
}