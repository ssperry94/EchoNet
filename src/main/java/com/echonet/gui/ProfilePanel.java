package com.echonet.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.echonet.domainmodel.User;
import com.echonet.utilities.Config;

public class ProfilePanel extends JPanel {
    private MainFrame mainWindow;
    private User currentUser;

    private JLabel titleLabel;
    private JLabel nameLabel, usernameLabel, birthdayLabel, emailLabel; // Labels displaying field names
    private JLabel nameDisplayLabel, usernameDisplayLabel, birthdayDisplayLabel, emailDisplayLabel; // Labels with user data

    private JButton backButton;
    private JButton logoutButton;

    private void initializeDisplayLabels(GridBagConstraints gbc) {
        this.nameDisplayLabel = new JLabel(this.currentUser.getFirstName() + " " + this.currentUser.getLastName());
        this.nameDisplayLabel.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(this.nameDisplayLabel, gbc);

        this.usernameDisplayLabel = new JLabel(this.currentUser.getUsername());
        this.usernameDisplayLabel.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 3;
        this.add(this.usernameDisplayLabel, gbc);

        this.birthdayDisplayLabel = new JLabel(this.currentUser.getBirthday());
        this.birthdayDisplayLabel.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 4;
        this.add(this.birthdayDisplayLabel, gbc);

        this.emailDisplayLabel = new JLabel(this.currentUser.getEmail());
        this.emailDisplayLabel.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 5;
        this.add(this.emailDisplayLabel, gbc);
    }

    private void initializeButtons(GridBagConstraints gbc) {
        // Panel to hold the buttons side by side
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(61, 63, 71)); // Match background color
        buttonPanel.setLayout(new GridBagLayout()); // Align buttons properly

        // Back to Home button
        this.backButton = new JButton("Home");
        this.backButton.addActionListener(e -> this.mainWindow.showPanel("HomePanel"));
        GridBagConstraints homeButtonGbc = new GridBagConstraints();
        homeButtonGbc.gridx = 0;
        homeButtonGbc.gridy = 0;
        homeButtonGbc.insets = new Insets(0, 5, 0, 5); // Add spacing
        buttonPanel.add(this.backButton, homeButtonGbc);

        // Logout button
        this.logoutButton = new JButton("Logout");
        this.logoutButton.addActionListener(e -> {
            Config.LOGGED_IN_USER = null; // Clear the logged-in user
            this.mainWindow.showPanel("LoginPanel"); // Navigate to LoginPanel
        });
        GridBagConstraints logoutButtonGbc = new GridBagConstraints();
        logoutButtonGbc.gridx = 1;
        logoutButtonGbc.gridy = 0;
        logoutButtonGbc.insets = new Insets(0, 5, 0, 5); // Add spacing
        buttonPanel.add(this.logoutButton, logoutButtonGbc);

        // Add the button panel to the main layout
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0);
        this.add(buttonPanel, gbc);
    }

    private void initialize() {
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(61, 63, 71));
        GridBagConstraints gbc = new GridBagConstraints();

        // Initialize labels
        this.titleLabel = new JLabel("My Profile", SwingConstants.CENTER);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        this.titleLabel.setFont(new Font("Monserrat", Font.BOLD, 50));
        this.titleLabel.setForeground((Color.WHITE));
        gbc.insets = new Insets(0, 0, 300, 0);
        this.add(this.titleLabel, gbc);

        this.nameLabel = new JLabel("Name: ");
        this.nameLabel.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(this.nameLabel, gbc);

        this.usernameLabel = new JLabel("Username: ");
        this.usernameLabel.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(this.usernameLabel, gbc);

        this.birthdayLabel = new JLabel("Birthday: ");
        this.birthdayLabel.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(this.birthdayLabel, gbc);

        this.emailLabel = new JLabel("Email: ");
        this.emailLabel.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(this.emailLabel, gbc);

        // Initialize buttons and display labels
        this.initializeButtons(gbc);
        this.initializeDisplayLabels(gbc);
    }

    public ProfilePanel(final MainFrame mw, final User u) {
        this.mainWindow = mw;
        this.currentUser = u;

        this.initialize();
    }
}
