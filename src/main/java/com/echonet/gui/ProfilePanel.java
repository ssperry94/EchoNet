package com.echonet.gui;

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

    private void initalize() {
        this.setLayout(new GridBagLayout());

        //initalize labels


    }

    public ProfilePanel(final MainFrame mw, final User u) {
        this.mainWindow = mw;
        this.currentUser = u;
        this.initalize();
    }

    public static void main(String args[]) {
        ProfilePanel p = new ProfilePanel(new MainFrame(), new User(1));
        p.setVisible(true);
    }
}
