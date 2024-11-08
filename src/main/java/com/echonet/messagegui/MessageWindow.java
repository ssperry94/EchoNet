package com.echonet.messagegui;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MessageWindow {
    private JFrame mainWindow;
    private JButton sendMessage;
    private JButton updateMessage;
    
    private void initalize() {
        mainWindow = new JFrame();
        mainWindow.setTitle("Messages");
        mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainWindow.setSize(800, 500);
        mainWindow.setLocationRelativeTo(null);
    }

    private void initalizeButtons() {
        
    }
    public MessageWindow() {
        this.initalize();
    }

    public void show() {
        mainWindow.setVisible(true);
    }

    //for running window
    public static void main(String args[]) {
        MessageWindow window = new MessageWindow();
        window.show();
    }
}
