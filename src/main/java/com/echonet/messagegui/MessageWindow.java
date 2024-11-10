package com.echonet.messagegui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MessageWindow {
    private JFrame mainWindow;
    private JPanel buttonPanel;
    private JPanel displayPanel;
    private JButton sendMessage;
    private JButton updateMessage;

    private void initalizeButtonsPanel() {
        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 200, 10));
        buttonPanel.setBackground(Color.GRAY);
        this.buttonPanel.add(sendMessage);
        this.buttonPanel.add(updateMessage);
    }

    private void initalizeMessagePanel() {
        this.displayPanel = new JPanel();
        this.displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.displayPanel.setBackground(Color.BLUE);
    }

    private void initalize() {
        this.mainWindow = new JFrame();
        this.mainWindow.setTitle("Messages");
        this.mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.mainWindow.setLayout(new BorderLayout(20,10));
        this.mainWindow.setSize(800, 500);
        this.mainWindow.setLocationRelativeTo(null);
        
        this.initalizeButtons();
        this.initalizeButtonsPanel();
        this.initalizeMessagePanel();

        this.mainWindow.add(buttonPanel, BorderLayout.SOUTH);
        this.mainWindow.add(displayPanel, BorderLayout.CENTER);

    }

    private void initalizeButtons() {
        this.sendMessage = new JButton("Send Message");
        this.updateMessage = new JButton("Update Messages");
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
