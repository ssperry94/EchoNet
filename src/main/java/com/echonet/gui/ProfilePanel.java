package com.echonet.gui;

import javax.swing.JPanel;

import com.echonet.domainmodel.User;

public class ProfilePanel extends JPanel {
    private MainFrame mainWindow;
    private User currentUser;

    private void initalize() {

    }
    public ProfilePanel(final MainFrame mw, final User u) {
        this.mainWindow = mw;
        this.currentUser = u;
        this.initalize();
    }

    public void main(String args[]) {
        this.setVisible(true);
    }
}
