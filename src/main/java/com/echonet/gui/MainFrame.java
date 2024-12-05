package com.echonet.gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.echonet.utilities.Config;

/**
 * MainFrame serves as the primary window for the EchoNet application.
 * It uses a CardLayout to dynamically switch between various panels.
 * 
 * Panels include:
 * - LoginPanel: For user login
 * - RegistrationPanel: For new user registration
 * - HomePanel: Post-login home screen
 * - Additional user-dependent panels (e.g., ProfilePanel, MessageWindow) are added dynamically after login.
 * 
 * @author Sidney Howard
 */
public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    /**
     * Constructs the MainFrame and initializes its layout and non-user-dependent panels.
     * The frame starts with the LoginPanel displayed.
     */
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
     * It initializes panels that require the current logged-in user's data.
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
     * Main method to run the EchoNet application.
     * It initializes and displays the MainFrame.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
