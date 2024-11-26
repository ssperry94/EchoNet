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
        this.setBackground(new Color(61, 63, 71));
        GridBagConstraints gbc = new GridBagConstraints();

        //initalize labels
        this.titleLabel = new JLabel("My Profile", SwingConstants.CENTER);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
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

    

        this.initalizeDisplayLabels();
        this.initalizeButtons();

    }

    public ProfilePanel(final MainFrame mw, final User u) {
        this.mainWindow = mw;
        this.currentUser = u;
        this.initalize();
    }
}
