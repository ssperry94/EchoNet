package com.echonet.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.echonet.domainmodel.User;

public class ProfilePanel extends JPanel {
    private MainFrame mainWindow;
    private User currentUser;

    private JLabel titleLabel;
    private JLabel nameLabel, usernameLabel, birthdayLabel, emailLabel; //labels displaying what each field is 
    private JLabel nameDisplayLabel, usernameDisplayLabel, birthdayDisplayLabel, emailDisplayLabel; //labels containing actual information of each field
    
    private JButton backButton;

    private void initalizeDisplayLabels() {

    }

    private void initalizeButtons() {

    }

    private void initalize() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //initalize labels
        this.titleLabel = new JLabel("My Profile");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(this.titleLabel, gbc);

        this.nameLabel = new JLabel("Name: ");
        this.usernameLabel = new JLabel("Username: ");
        this.birthdayLabel = new JLabel("Birthday: ");
        this.emailLabel = new JLabel("Email: ");

        
        this.add(this.titleLabel);

        this.initalizeDisplayLabels();
        this.initalizeButtons();

    }

    public ProfilePanel(final MainFrame mw, final User u) {
        this.mainWindow = mw;
        this.currentUser = u;
        this.initalize();
    }
}
