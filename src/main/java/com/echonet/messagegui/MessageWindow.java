package com.echonet.messagegui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.echonet.domainmodel.Message;

/* TODO: adjust DataPipe to read multiple messages at once.
 * adjust Backend System to read by any subclass of domain by any column names (ie postID, messageID, timestamp, etc)
 */

public class MessageWindow {
    private MessageComposer composer;
    private JFrame mainWindow;
    private JPanel buttonPanel;
    private JPanel displayPanel;
    private JButton sendMessage;
    private JButton updateMessage;
    private JEditorPane messageDisplay;

    private void initalizeButtonsPanel() {
        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 200, 10));
        buttonPanel.setBackground(Color.GRAY);
        this.buttonPanel.add(sendMessage);
        this.buttonPanel.add(updateMessage);
    }

    private void initalizeMessagePanel() throws Exception{
        Message m = new Message(1);
        m.setContents("Hello!");
        this.messageDisplay = new JEditorPane();
        messageDisplay.setEditable(false);
        messageDisplay.setText(m.getContents());

        this.displayPanel = new JPanel();
        this.displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.displayPanel.setBackground(Color.BLUE);
        this.displayPanel.add(messageDisplay);
    }

    private void initalize() throws Exception {
        this.mainWindow = new JFrame();
        this.mainWindow.setTitle("Messages");
        this.mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.mainWindow.setLayout(new BorderLayout(20,10));
        this.mainWindow.setSize(800, 500);
        this.mainWindow.setLocationRelativeTo(null);

        this.initalizeButtons();
        this.initalizeButtonsPanel();
        this.initalizeMessagePanel();

        this.mainWindow.addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosed(WindowEvent e) {
                if(composer.getMainWindow() != null) {
                    composer.getMainWindow().dispose();
                }
            }
        });

        this.mainWindow.add(buttonPanel, BorderLayout.SOUTH);
        this.mainWindow.add(displayPanel, BorderLayout.CENTER);

    }

    private void initalizeButtons() {
        this.sendMessage = new JButton("Send Message");
        this.sendMessage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                composer = new MessageComposer();
                composer.show();
            }
        });
        this.updateMessage = new JButton("Update Messages");
    }

    public MessageWindow() throws Exception{
        this.initalize();
    }

    public void show() {
        mainWindow.setVisible(true);
    }

    //for running window
    public static void main(String args[]) {
        try {
            MessageWindow window = new MessageWindow();
            window.show();
        } catch (Exception e) {
            e.printStackTrace();

    }
    }
}
