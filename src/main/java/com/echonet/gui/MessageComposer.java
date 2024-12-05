package com.echonet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.echonet.datahandling.DataPipe;
import com.echonet.datahandling.Table;
import com.echonet.domainmodel.Message;
import com.echonet.domainmodel.User;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MessageComposer {

    private User currentUser;
    private JFrame mainWindow;
    private JPanel composerPanel;
    private JPanel buttonPanel;
    private JEditorPane messageBox;
    private JButton sendButton;
    private JEditorPane recipiantBox;
    private JLabel recipantLabel;
    private JLabel contentsLabel;
    private JScrollPane contentsScrollBar;

    private boolean botIDCheck(final int recipiantID) {
        boolean isUserBot;
        switch(this.currentUser.getID()) {
            case 1: isUserBot = (recipiantID == 2 || recipiantID == 3); break;
            case 2: isUserBot = (recipiantID == 1 || recipiantID == 3); break;
            case 3: isUserBot = (recipiantID == 1 || recipiantID == 2); break;
            default: isUserBot = false; break;
        }
        
        return isUserBot;
    }
 
    private void botRespond(final int recipiantID, final DataPipe pipe) {
        List <String> responses = new ArrayList<>(List.of("Hello!", "How are you?", "I'm good!", "Glad to hear it!", "Tell me more.", "I'm not sure"));
        Message m;
        if(this.botIDCheck(recipiantID)) {
            //send a response back 
            try {
                m = new Message(this.currentUser.getID());
                m.setAutomaticMessageID();
                m.setContents(responses.get(ThreadLocalRandom.current().nextInt(0, responses.size() -1)));
                m.setTimeStamp();
                m.setRecipiantID(recipiantID);
                pipe.write(m);
            } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
                return; //error occured, bot won't respond
            }
        }
        else {
            return; //do nothing if message wasn't sent to a bot
        }
    }
    
    private void sendMessage() {
        DataPipe dataPipe = new DataPipe();
        User u;
        Message message;
        //check to make sure recipiant exists
        String recipiantUsername = this.recipiantBox.getText();

        //if nothing was entered
        if(recipiantUsername.equals("")) {
            JOptionPane.showMessageDialog(mainWindow, "Error, must have recipiant.", "RECIPIANTERROR", JOptionPane.ERROR_MESSAGE);
            this.messageBox.setText("");
            return;
        }
        try {
            u = new User(1); //will be able to change when constructor is fixed
            u.setTable(new Table(Config.USER_TABLE));
    
            Map <String, Object> userMap = dataPipe.read(u, "username", recipiantUsername);
            if(userMap == null) {
                JOptionPane.showMessageDialog(mainWindow, "Cannot find given user. Please make sure that you entered the correct username.", "RECIPIANTNOTFOUND", JOptionPane.ERROR_MESSAGE);
                this.messageBox.setText("");
                return;
            }
            String actualUsername = (String) userMap.get("username");
            if(!actualUsername.equals(recipiantUsername)) {
                JOptionPane.showMessageDialog(mainWindow, "Internal error occured. Message failed to send", "USERNAMEMISMATCH", JOptionPane.ERROR_MESSAGE);
                this.messageBox.setText("");
                return;
            }
            message = new Message((int) userMap.get("userID"));

            message.setContents(this.messageBox.getText());
            message.setAutomaticMessageID();
            message.setTimeStamp();
            message.setRecipiantID(this.currentUser.getID());

            if(dataPipe.write(message)) {
                this.messageBox.setText("");
                this.botRespond((int) userMap.get("userID"), dataPipe); 
                JOptionPane.showMessageDialog(mainWindow, "Message Sent!", "MESSAGESUCCESS", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(this.mainWindow, "Message could not be send.", "UKNOWNMESSAGEERROR", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainWindow, "Unknown Error occured. Message failed to send", "UNKOWNERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void initalizeEditors() {
        this.recipantLabel = new JLabel();
        this.recipantLabel.setText("To:");

        this.recipiantBox = new JEditorPane();
        this.recipiantBox.setMaximumSize(new Dimension(300, 20));
        this.recipiantBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.contentsLabel = new JLabel();
        this.contentsLabel.setText("Contents:");

        this.messageBox = new JEditorPane();
        this.messageBox.setPreferredSize(new Dimension(500,500));
        this.messageBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.contentsScrollBar = new JScrollPane(this.messageBox);

        this.composerPanel.add(this.recipantLabel);
        this.composerPanel.add(this.recipiantBox);
        this.composerPanel.add(this.contentsLabel);
        this.composerPanel.add(this.contentsScrollBar);
    }

    private void initalizeButtons() {
        this.sendButton = new JButton();
        this.sendButton.setText("Send");
        this.sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               sendMessage();
            }
        });

        this.buttonPanel.add(this.sendButton);
    }

    private void initalizePanels() {
        this.composerPanel = new JPanel();
        this.composerPanel.setLayout(new BoxLayout(composerPanel, BoxLayout.Y_AXIS));

        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    }

    private void initalize() {
        this.mainWindow = new JFrame();
        this.mainWindow.setTitle("Message Composer");
        this.mainWindow.setLayout(new BorderLayout(10, 10));
        this.mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.mainWindow.setSize(500,500);

        //initalize message box
        this.initalizePanels();
        //initalize buttons
        this.initalizeButtons();
        //initalize recipant box
        this.initalizeEditors();

        this.mainWindow.add(this.buttonPanel, BorderLayout.SOUTH);
        this.mainWindow.add(this.composerPanel, BorderLayout.CENTER);
    }

    public MessageComposer(final User currentUser) {
        this.currentUser = currentUser;
        this.initalize();
    }

    public void show() {
        this.mainWindow.setVisible(true);
    }

    public JFrame getMainWindow() {
        return this.mainWindow;
    }
}