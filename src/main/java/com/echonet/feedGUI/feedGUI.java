
package com.echonet.feedGUI;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class feedGUI {

    public feedGUI() {
        
        // **WINDOW
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Feed");

        // **Username Label
        JLabel usernameLabel = new JLabel("Username: jack"); // 
        frame.add(usernameLabel, BorderLayout.NORTH); // Add the username label to the top of the frame

        // *TextBox
        JTextField textBox = new JTextField(20); // Create a text box with a width of 20 columns
        panel.add(textBox); // Add the text box to the panel

        // *Button
        JButton button = new JButton("Upload Post");

        // listens (waits) for action
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userInput = textBox.getText(); // Get text entered in the text box
                System.out.println("Post Uploaded: " + userInput);
                
                //make feed object
                feed test = new feed();
                test.uploadNewPost(userInput);
                test.test();  // Calls method
            }
        });

        panel.add(button);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new feedGUI();
    }
}
