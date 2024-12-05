package com.echonet.gui;

import java.util.ArrayList;
import java.util.List;

public class Feed {

    // attributes to store posts and messages
    public List<String> posts;
    private List<String> messages;

    // Constructor
     public Feed() {
        posts = new ArrayList<>();
        messages = new ArrayList<>();
    }

    // Method to add a new post
    public void uploadNewPost(String content) {
        posts.add(content);
        System.out.println("New post uploaded: " + content);
    }

    // Method to display all posts
    public void displayPosts() {
        System.out.println("Feed posts:");
        for (String post : posts) {
            System.out.println(post);
        }
    }

    // Method to send a message
    public void sendMessage(String message) {
        messages.add(message);
        System.out.println("Message sent: " + message);
    }

    // Method to display all messages
    public void displayMessages() {
        System.out.println("Messages:");
        for (String message : messages) {
            System.out.println(message);
        }
    }

    public void test() {
        System.out.println("test successful!");
        
    }
}