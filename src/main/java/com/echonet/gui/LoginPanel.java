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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.echonet.domainmodel.Authentication;

public class LoginPanel extends JPanel {

    private MainFrame mainFrame;
    private Image backgroundImage;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        // Load the background image
        backgroundImage = new ImageIcon(getClass().getResource("/loginbackground.png")).getImage();

        // Set layout
        setLayout(new GridBagLayout());

        // Components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        // Change text color
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        usernameField.setForeground(Color.BLACK);
        usernameField.setBackground(Color.LIGHT_GRAY);
        passwordField.setForeground(Color.BLACK);
        passwordField.setBackground(Color.LIGHT_GRAY);
        loginButton.setForeground(Color.BLACK);
        registerButton.setForeground(Color.BLACK);

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

        // Login button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);

        // Register button
        gbc.gridy = 4;
        add(registerButton, gbc);

        // Action Listeners
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            try {
                Authentication auth = new Authentication(0); 
                if (auth.login(username, password)) {
                    mainFrame.showPanel("HomePanel");
                } else {
                    JOptionPane.showMessageDialog(this, "Login failed. Please check your credentials.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred during login: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        registerButton.addActionListener(e -> mainFrame.showPanel("RegistrationPanel"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image, scaled to fit the panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
