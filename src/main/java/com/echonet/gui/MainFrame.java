package com.echonet.gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.echonet.domainmodel.User;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private User loggedInUser; // Store the logged-in user

    public MainFrame() {
        // Setup the main frame
        setTitle("EchoNet");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize the card layout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize all panels
        LoginPanel loginPanel = new LoginPanel(this);
        RegistrationPanel registrationPanel = new RegistrationPanel(this);
        HomePanel homePanel = new HomePanel(this);

        try {
            // Add all panels to the main panel
            mainPanel.add(loginPanel, "LoginPanel");
            mainPanel.add(registrationPanel, "RegistrationPanel");
            mainPanel.add(homePanel, "HomePanel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add the main panel to the frame
        add(mainPanel);
        showPanel("LoginPanel"); // Start with the LoginPanel
    }

    // Method to switch between panels
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    // Set the logged-in user
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    // Get the logged-in user
    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
