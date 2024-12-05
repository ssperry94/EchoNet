package com.echonet.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.echonet.datahandling.Table;
import com.echonet.domainmodel.Authentication;
import com.echonet.domainmodel.User;
import com.echonet.utilities.Config;

/**
 * RegistrationPanel provides the user interface for new users to register an account in the EchoNet application.
 * It allows users to input their details such as name, email, birthday, username, and password.
 * Registration success or failure is handled with appropriate messages.
 * 
 * The panel also includes a background image and navigation to the LoginPanel.
 * 
 * @author Sidney Howard
 */

public class RegistrationPanel extends JPanel {

    private MainFrame mainFrame;
    private Image backgroundImage;

    /**
     * Constructs a RegistrationPanel and initializes its layout and components.
     * Provides fields for user details and buttons to register or navigate back to the login screen.
     * 
     * @param mainFrame the main application frame that controls panel transitions
     */
    
    public RegistrationPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        // Load the background image
        backgroundImage = new ImageIcon(getClass().getResource("/loginbackground.png")).getImage();

        // Set layout
        setLayout(new GridBagLayout());

        // Components
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel birthdayLabel = new JLabel("Birthday (YYYY-MM-DD):");
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");

        JTextField firstNameField = new JTextField(15);
        JTextField lastNameField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        JTextField birthdayField = new JTextField(15);
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JPasswordField confirmPasswordField = new JPasswordField(15);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Login");

        // Change text color
        firstNameLabel.setForeground(Color.WHITE);
        lastNameLabel.setForeground(Color.WHITE);
        emailLabel.setForeground(Color.WHITE);
        birthdayLabel.setForeground(Color.WHITE);
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        confirmPasswordLabel.setForeground(Color.WHITE);

        // Layout setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // First Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(firstNameLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(firstNameField, gbc);

        // Last Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(lastNameLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(lastNameField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(emailLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(emailField, gbc);

        // Birthday
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(birthdayLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(birthdayField, gbc);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(passwordField, gbc);

        // Confirm Password
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(confirmPasswordField, gbc);

        // Register button
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(registerButton, gbc);

        // Back button
        gbc.gridy = 8;
        add(backButton, gbc);

        // Action Listeners
        registerButton.addActionListener(e -> {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String email = emailField.getText().trim();
            String birthday = birthdayField.getText().trim();
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Create a new User object
                User newUser = new User(ThreadLocalRandom.current().nextInt(4, 1000)); // ID will be auto-generated
                newUser.setTable(new Table(Config.USER_TABLE));
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setEmail(email);
                newUser.setBirthday(birthday);
                newUser.setUsername(username);

                Authentication auth = new Authentication(newUser.getID());

                if (auth.Register(username, password, newUser)) {
                    JOptionPane.showMessageDialog(this, "Registration successful! Please log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    mainFrame.showPanel("LoginPanel");
                } else {
                    JOptionPane.showMessageDialog(this, "Registration failed. Username may already exist.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred during registration: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        backButton.addActionListener(e -> mainFrame.showPanel("LoginPanel"));
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
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
