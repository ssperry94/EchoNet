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
import com.echonet.utilities.Config;

/**
 * LoginPanel provides the user interface for logging into the EchoNet application.
 * It allows users to enter their username and password to authenticate or navigate to the registration panel.
 * The panel includes a background image for visual appeal.
 * 
 * Features:
 * - Username and password fields for user authentication.
 * - Buttons for logging in and navigating to the registration panel.
 * - Dynamic loading of user-specific panels upon successful login.
 * 
 * @author Sidney Howard
 */
public class LoginPanel extends JPanel {

    private MainFrame mainFrame;
    private Image backgroundImage;

    /**
     * Constructs a LoginPanel and initializes its layout and components.
     * The panel includes fields for username and password, and buttons for login and registration.
     * 
     * @param mainFrame the main application frame that controls panel transitions
     */
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
                Config.LOGGED_IN_USER = auth.login(username, password); 
                if (Config.LOGGED_IN_USER != null) {
                    mainFrame.initializeUserPanels(); // Dynamically add user-dependent panels
                    mainFrame.showPanel("HomePanel"); // Redirect to the HomePanel
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid login. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred during login: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        registerButton.addActionListener(e -> mainFrame.showPanel("RegistrationPanel"));
    }

    /**
     * Custom painting for the panel to render a background image.
     * The image is scaled to fit the panel's dimensions.
     * 
     * @param g the Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image, scaled to fit the panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
