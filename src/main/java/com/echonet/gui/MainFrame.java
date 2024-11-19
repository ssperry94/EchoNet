package com.echonet.gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
    

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {

        //CardLayout cardLayout; 
        //JPanel mainPanel;

        setTitle("EchoNet");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize the panels
        LoginPanel loginPanel = new LoginPanel(this);
        
        RegistrationPanel registrationPanel = new RegistrationPanel(this);
        HomePanel homePanel = new HomePanel(this);
        

         
        // Add panels to main panel
        mainPanel.add(loginPanel, "LoginPanel");
        
        mainPanel.add(registrationPanel, "RegistrationPanel");
        mainPanel.add(homePanel, "HomePanel");
     
        add(mainPanel);
        showPanel("LoginPanel");
    }

    public void showPanel(String panelName) {
        this.cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
