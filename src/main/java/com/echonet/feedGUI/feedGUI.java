
//package com.echonet.feedGUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class feedGUI {

    public feedGUI() {
        // Window setup
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        mainPanel.setLayout(new BorderLayout());

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Feed");

        // Set fixed size for the window
        frame.setSize(400, 500);
        frame.setResizable(false);

        // Panel to hold posts (inside a scroll pane)
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new GridLayout(0, 1, 0, 10));  // Use vertical layout with spacing between posts

        // Scroll pane for postPanel to make it scrollable
        JScrollPane scrollPane = new JScrollPane(postPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scrollPane, BorderLayout.CENTER);  // Add the scroll pane to the main panel

        // Panel to hold the text field and button at the bottom
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        // Textbox for user input
        JTextField textField = new JTextField(20);  // Creates a textbox with 20 columns
        inputPanel.add(textField, BorderLayout.CENTER);  // Adds the textbox to the center of input panel

        // Button
        JButton button = new JButton("POST");

        //User
      

        // Listener for button action
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredText = textField.getText().trim();  // Retrieves text from textbox
                if (!enteredText.isEmpty()) {
                    // Get current date and time
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String timestamp = now.format(formatter);

                    // Format text with timestamp and fixed width
                    String formattedText = "<html><div style='width:250px;'><b>[" + timestamp + "]</b><br>" + enteredText + "</div></html>";

                    // Create a label with fixed size
                    JLabel postLabel = new JLabel(formattedText);
                    postLabel.setPreferredSize(new Dimension(300, 60));  // Fixed width and height for each post
                    postLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));  // Padding around the text
                    postPanel.add(postLabel);  // Add new post to postPanel

                    textField.setText("");  // Clear the textbox after submission
                    frame.revalidate();  // Refreshes the frame to show the new post
                    frame.repaint();  // Ensures the scroll pane updates if needed
                }
            }
        });

        inputPanel.add(button, BorderLayout.EAST);  // Place the button to the right of the text field

        // Add inputPanel (with text field and button) to the bottom of mainPanel
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new feedGUI();  
    }
}
