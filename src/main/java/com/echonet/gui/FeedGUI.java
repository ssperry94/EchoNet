package com.echonet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.echonet.datahandling.DataPipe;
import com.echonet.domainmodel.Post;

public class FeedGUI {

    private JPanel mainPanel; // Expose this panel for integration
    private Feed feed; // Reference to the feed backend
    private JPanel postPanel;
    private String selectedImagePath = null; // Path of the selected image
    private String currentUser = "John Doe"; // Dummy user (replace with dynamic user)

    public FeedGUI(Feed feed) {
        this.feed = feed; // Assign the feed object

        // Define custom colors
        Color mainBackground = new Color(61, 63, 71); // Matches HomePanel
        Color secondaryColor = new Color(146, 199, 195); // Secondary color for text

        // Create main panel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(mainBackground);

        // Container panel to center the feed and add padding
        JPanel centeredContainer = new JPanel(new BorderLayout());
        centeredContainer.setBackground(mainBackground);
        centeredContainer.setBorder(BorderFactory.createEmptyBorder(0, 150, 0,150)); // Add padding on left and right

        // Panel to hold posts
        postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS)); // Vertical layout for posts
        postPanel.setBackground(mainBackground);

        // Scroll pane for postPanel
        JScrollPane scrollPane = new JScrollPane(postPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(mainBackground);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        centeredContainer.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centeredContainer, BorderLayout.CENTER);

        // Panel for text field, upload button, and post button
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(mainBackground);

        // Text field for user input
        JTextField textField = new JTextField(20);
        inputPanel.add(textField, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(mainBackground);

        // Image upload button
        JButton uploadButton = new JButton("Upload Image");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(mainPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    selectedImagePath = selectedFile.getAbsolutePath();
                    JOptionPane.showMessageDialog(mainPanel, "Image selected: " + selectedFile.getName());
                }
            }
        });
        buttonPanel.add(uploadButton);

        // Post button
JButton postButton = new JButton("POST");
postButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String enteredText = textField.getText().trim();

        if (!enteredText.isEmpty() || selectedImagePath != null) {
            try {
                // Create a new Post object
                Post post = new Post(1); // Assuming userID = 1 for now (replace with dynamic userID)
                post.setContent(enteredText);
                post.setTimestamp(); // Set current timestamp
                post.setAutomaticPostID(); // Generate a random post ID
                if (selectedImagePath != null) {
                    post.setImagePath(selectedImagePath);
                }

                // Save the Post object to the database
                DataPipe dataPipe = new DataPipe();
                boolean success = dataPipe.write(post);

                if (success) {
                    JOptionPane.showMessageDialog(mainPanel, "Post saved successfully!");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Failed to save post.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Create a container panel for the post
                JPanel postContainer = new JPanel();
                postContainer.setLayout(new BoxLayout(postContainer, BoxLayout.Y_AXIS)); // Vertical layout for all elements
                postContainer.setBackground(mainBackground);
                postContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                // Add user and timestamp label
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                JLabel userTimeLabel = new JLabel("<html><b>" + currentUser + "</b> • " + timestamp + "</html>");
                userTimeLabel.setForeground(secondaryColor);
                userTimeLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to the left
                postContainer.add(userTimeLabel);
                postContainer.add(Box.createRigidArea(new Dimension(0, 5))); // Spacing below user and time

                // Add image to the post (if selected)
                if (selectedImagePath != null) {
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedImagePath).getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH)); // Enlarged image
                    JLabel imageLabel = new JLabel(imageIcon);
                    imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to the left
                    postContainer.add(imageLabel);
                    postContainer.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between image and caption
                }

                // Add caption to the post (if entered)
                if (!enteredText.isEmpty()) {
                    JLabel captionLabel = new JLabel("<html><div style='width:400px;'>" + enteredText + "</div></html>");
                    captionLabel.setForeground(secondaryColor);
                    captionLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to the left
                    postContainer.add(captionLabel);
                }

                // Add the post container to the feed
                postPanel.add(postContainer);
                postPanel.revalidate();
                postPanel.repaint();

                // Clear input fields
                textField.setText("");
                selectedImagePath = null;

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(mainPanel, "Error saving the post to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
});
buttonPanel.add(postButton);

        inputPanel.add(buttonPanel, BorderLayout.EAST);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
