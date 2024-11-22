package com.echonet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.echonet.datahandling.DataPipe;
import com.echonet.datahandling.Table;
import com.echonet.domainmodel.Message;
import com.echonet.domainmodel.User;
import com.echonet.utilities.Config;

public class MessageComposer {

    private User currentUser;
    private JFrame mainWindow;
    private JPanel composerPanel;
    private JPanel buttonPanel;
    private JEditorPane messageBox;
    private JButton sendButton;
    private JButton updateButton; // Added Update Messages button
    private JEditorPane recipientBox;
    private JLabel recipientLabel;
    private JLabel contentsLabel;
    private JScrollPane contentsScrollBar;

    public MessageComposer(final User currentUser) {
        this.currentUser = currentUser;
        this.initialize();
    }

    public void show() {
        this.mainWindow.setVisible(true);
    }

    public JFrame getMainWindow() {
        return this.mainWindow;
    }

    private void sendMessage() {
        // Placeholder logic for sending messages
        System.out.println("Message Sent!");
        JOptionPane.showMessageDialog(mainWindow, "Message Sent!", "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateMessages() {
        // Placeholder logic for updating messages
        System.out.println("Messages Updated!");

        // Play sound effect when messages are updated
        playSound("echo.wav");

        JOptionPane.showMessageDialog(mainWindow, "Messages Updated!", "Update", JOptionPane.INFORMATION_MESSAGE);
    }

    private void playSound(String soundFileName) {
        try {
            File soundFile = new File(soundFileName); // Ensure the file path is correct
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));
            clip.start(); // Play the sound
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void initializeEditors() {
        this.recipientLabel = new JLabel("To:");
        this.recipientBox = new JEditorPane();
        this.recipientBox.setMaximumSize(new Dimension(300, 20));
        this.recipientBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.contentsLabel = new JLabel("Contents:");
        this.messageBox = new JEditorPane();
        this.messageBox.setPreferredSize(new Dimension(500, 500));
        this.messageBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.contentsScrollBar = new JScrollPane(this.messageBox);

        this.composerPanel.add(this.recipientLabel);
        this.composerPanel.add(this.recipientBox);
        this.composerPanel.add(this.contentsLabel);
        this.composerPanel.add(this.contentsScrollBar);
    }

    private void initializeButtons() {
        this.sendButton = new JButton("Send");
        this.sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        this.updateButton = new JButton("Update Messages"); // Update Messages button
        this.updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMessages(); // Play sound and update messages
            }
        });

        this.buttonPanel.add(this.sendButton);
        this.buttonPanel.add(this.updateButton); // Add Update Messages button
    }

    private void initializePanels() {
        this.composerPanel = new JPanel();
        this.composerPanel.setLayout(new BoxLayout(composerPanel, BoxLayout.Y_AXIS));

        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    }

    private void initialize() {
        this.mainWindow = new JFrame();
        this.mainWindow.setTitle("Message Composer");
        this.mainWindow.setLayout(new BorderLayout(10, 10));
        this.mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.mainWindow.setSize(500, 500);

        this.initializePanels();
        this.initializeButtons();
        this.initializeEditors();

        this.mainWindow.add(this.buttonPanel, BorderLayout.SOUTH);
        this.mainWindow.add(this.composerPanel, BorderLayout.CENTER);
    }
}
