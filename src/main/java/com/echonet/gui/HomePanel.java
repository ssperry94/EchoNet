package com.echonet.gui;

import com.echonet.feedGUI.Feed;
import com.echonet.feedGUI.FeedGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HomePanel extends JPanel {

    private MainFrame mainFrame;

    public HomePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout()); // Ensure proper containment of components

        // Background color
        this.setBackground(new Color(61, 63, 71));

        // Header Panel for buttons and title
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false); // Transparent to match background

        // View Profile button (top-left)
        JButton profileButton = new JButton("View Profile");
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false); // Transparent to match background
        leftPanel.add(profileButton);
        headerPanel.add(leftPanel, BorderLayout.WEST);

        // "echonet" title (center)
        JLabel welcomeLabel = new JLabel("echonet", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Monserrat", Font.BOLD, 30));
        welcomeLabel.setForeground(new Color(146, 199, 195));
        headerPanel.add(welcomeLabel, BorderLayout.CENTER);

        // Messages button (top-right)
        JButton messagesButton = new JButton("EchoChanmbers");
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false); // Transparent to match background
        rightPanel.add(messagesButton);
        headerPanel.add(rightPanel, BorderLayout.EAST);

        // Add components to the panel
        add(headerPanel, BorderLayout.NORTH); // Add header to the top
     

        // Button Listeners
        profileButton.addActionListener(e -> mainFrame.showPanel("ProfilePanel"));
        messagesButton.addActionListener(e -> mainFrame.showPanel("MessagePanel"));
    }
}
