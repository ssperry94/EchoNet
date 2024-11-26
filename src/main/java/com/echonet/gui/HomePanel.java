package com.echonet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HomePanel extends JPanel {

    private MainFrame mainFrame;

    public HomePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        // Set background color for the entire panel
        this.setBackground(new Color(61, 63, 71)); // Custom color #92c7c3

        // Welcome Label at the top
        JLabel welcomeLabel = new JLabel("echonet", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Monserrat", Font.BOLD, 50));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        welcomeLabel.setForeground(new Color(146, 199, 195));

        // Buttons
        JButton profileButton = new JButton("View Profile");
        JButton friendsButton = new JButton("Friends List");
        JButton messagesButton = new JButton("Messages");
        JButton postButton = new JButton("Create Post");

        // Button Panel at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center buttons with spacing
        buttonPanel.setOpaque(false); // Make button panel transparent to show parent background
        buttonPanel.add(profileButton);
        buttonPanel.add(friendsButton);
        buttonPanel.add(messagesButton);
        buttonPanel.add(postButton);

        // Add components to the panel
        add(welcomeLabel, BorderLayout.NORTH); // Welcome label at the top
        add(buttonPanel, BorderLayout.SOUTH); // Buttons moved to the bottom

        // Action Listeners for Buttons
        profileButton.addActionListener(e -> mainFrame.showPanel("ProfilePanel"));
        friendsButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Friends List coming soon!"));
        postButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Create Post coming soon!"));

        // Action Listener for Messages Button
        messagesButton.addActionListener(e -> {
            // Navigate to the MessageWindow panel
            mainFrame.showPanel("MessagePanel");
        });
    }
}
