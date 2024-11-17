package com.echonet.messagegui;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.echonet.datahandling.DataPipe;
import com.echonet.domainmodel.User;

public class MessageComposer {

    private JFrame mainWindow;
    private JPanel composerPanel;
    private JPanel buttonPanel;
    private JEditorPane messageBox;
    private JButton sendButton;
    private JEditorPane recipiantBox;
    private JLabel recipantLabel;
    private JLabel contentsLabel;
    private JScrollPane contentsScrollBar;

    private boolean sendMessage() {
        DataPipe dataPipe = new DataPipe();
        //check to make sure recipiant exists
        String recipiantUsername = this.recipiantBox.getText();

        if(recipiantUsername == null) {
            return false;
        }

        Map <String, Object> userMap = dataPipe.read(new User(1), "username", recipiantUsername);

        if(userMap == null || userMap.get("username") != recipiantUsername) {
            return false;
        }
        //create message from recipant and conent
            //add timestamp
        
        //dataPipe.write(message); 
        return true;
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
                if(sendMessage()) {
                    System.out.println("Success");
                }
                else {
                    System.err.println("Fail");
                }
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

    public MessageComposer() {
        this.initalize();
    }

    public void show() {
        this.mainWindow.setVisible(true);
    }

    public JFrame getMainWindow() {
        return this.mainWindow;
    }
}
