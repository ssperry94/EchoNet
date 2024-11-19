package com.echonet.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.echonet.domainmodel.User;

public class HomePanel extends JPanel {

    private MainFrame mainFrame;
    private User currentUser;

    public HomePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.currentUser = new User(1); // Example user ID, replace with actual logic
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to EchoNet!", SwingConstants.CENTER);
        JButton profileButton = new JButton("View Profile");
        JButton friendsButton = new JButton("Friends List");
        JButton messagesButton = new JButton("Messages");
        JButton postButton = new JButton("Create Post");

        // Adding components to the layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(profileButton);
        buttonPanel.add(friendsButton);
        buttonPanel.add(messagesButton);
        buttonPanel.add(postButton);

        add(welcomeLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Action Listener for Messages Button
        messagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MessageWindow messageWindow = new MessageWindow(currentUser);
                    messageWindow.show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(HomePanel.this, "Failed to open messages!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
