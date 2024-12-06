package com.echonet.gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.echonet.utilities.Config;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        // Setup the main frame
        setTitle("EchoNet");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize the card layout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize only non-user-dependent panels
        mainPanel.add(new LoginPanel(this), "LoginPanel");
        mainPanel.add(new RegistrationPanel(this), "RegistrationPanel");
        mainPanel.add(new HomePanel(this), "HomePanel");

        // Add the main panel to the frame
        add(mainPanel);
        showPanel("LoginPanel"); // Start with the LoginPanel
    }

    /**
     * Dynamically adds user-dependent panels after login.
     * This method should be called after the user logs in.
     */
    public void initializeUserPanels() {
        try {
            // Add panels that require a logged-in user
            mainPanel.add(new MessageWindow(Config.LOGGED_IN_USER, this), "MessagePanel");
            mainPanel.add(new ProfilePanel(this, Config.LOGGED_IN_USER), "ProfilePanel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Switches to a specified panel in the card layout.
     *
     * @param panelName the name of the panel to display
     */
    public void showPanel(String panelName) {
        System.out.println("Switching to: " + panelName); // Debugging log
        cardLayout.show(mainPanel, panelName);
    }

    /**
     * Main method to run the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
