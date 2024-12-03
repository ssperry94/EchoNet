package com.echonet.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegistrationPanel extends JPanel {

    private MainFrame mainFrame;
    private Image backgroundImage;

    public RegistrationPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        // Load the background image
        backgroundImage = new ImageIcon(getClass().getResource("/loginbackground.png")).getImage();

        // Set layout
        setLayout(new GridBagLayout());

        // Components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JPasswordField confirmPasswordField = new JPasswordField(15);
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Login");

        // Change text color
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        confirmPasswordLabel.setForeground(Color.WHITE);
        usernameField.setForeground(Color.BLACK);
        usernameField.setBackground(Color.LIGHT_GRAY);
        passwordField.setForeground(Color.BLACK);
        passwordField.setBackground(Color.LIGHT_GRAY);
        confirmPasswordField.setForeground(Color.BLACK);
        confirmPasswordField.setBackground(Color.LIGHT_GRAY);
        registerButton.setForeground(Color.BLACK);
        backButton.setForeground(Color.BLACK);

        // Layout setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Spacer to move components down
        JLabel spacer = new JLabel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span two columns
        gbc.weighty = 1.0; // Add vertical weight to push components down
        add(spacer, gbc);

        // Username
        gbc.weighty = 0; // Reset weight for actual components
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(passwordField, gbc);

        // Confirm Password
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(confirmPasswordField, gbc);

        // Register button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(registerButton, gbc);

        // Back button
        gbc.gridy = 5;
        add(backButton, gbc);

        // Action Listeners
        registerButton.addActionListener(e -> mainFrame.showPanel("HomePanel"));
        backButton.addActionListener(e -> mainFrame.showPanel("LoginPanel"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image, scaled to fit the panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
